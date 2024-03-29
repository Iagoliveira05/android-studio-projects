package com.example.listatelefonica.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.example.listatelefonica.R
import com.example.listatelefonica.database.DBHelper
import com.example.listatelefonica.databinding.ActivityContactDetailBinding
import com.example.listatelefonica.model.ContactModel

class ContactDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailBinding
    private lateinit var db: DBHelper
    private var contactModel = ContactModel()

    private lateinit var launcher: ActivityResultLauncher<Intent>
    private var imageId: Int? = -1

    private val REQUEST_PHONE_CALL = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent
        val id = i.extras?.getInt("id")

        db = DBHelper(applicationContext)


        if (id != null) {
            contactModel = db.getContact(id)
            populate()
        } else {
            finish()
        }

        binding.imageEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(contactModel.email))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Sent by ListaTelefonica APP")

            try {
                startActivity(Intent.createChooser(emailIntent, "Choose Email Client..."))
            } catch (e: Exception) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.imagePhone.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
                ) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_PHONE_CALL)
            } else {
                val dialIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+contactModel.phone))
                startActivity(dialIntent)
            }
        }

        binding.buttonBack.setOnClickListener {
            setResult(0, i)
            finish()
        }

        binding.buttonEdit.setOnClickListener {
            binding.layoutEditDelete.visibility = View.VISIBLE
            binding.layoutEdit.visibility = View.GONE

            changeEditText(true)
        }

        binding.buttonCancel.setOnClickListener {
            binding.layoutEditDelete.visibility = View.GONE
            binding.layoutEdit.visibility = View.VISIBLE

            populate()
            changeEditText(false)
        }

        binding.buttonSave.setOnClickListener {
            val res = db.updateContact(
                id = contactModel.id,
                name = binding.editName.text.toString(),
                address = binding.editAddress.text.toString(),
                email = binding.editEmail.text.toString(),
                phone = binding.editPhone.text.toString().toInt(),
                imageId = if (imageId!! > 0) {
                    imageId!!.toInt()
                } else {
                    contactModel.imageId
                }
            )

            if (res > 0) {
                Toast.makeText(applicationContext, "Update OK", Toast.LENGTH_SHORT).show()
                setResult(1, i)
                finish()
            } else {
                Toast.makeText(applicationContext, "Update Error", Toast.LENGTH_SHORT).show()
                setResult(0, i)
                finish()
            }
        }

        binding.buttonDelete.setOnClickListener {
            val res = db.deleteContact(contactModel.id)

            if (res > 0) {
                Toast.makeText(applicationContext, "Delete OK", Toast.LENGTH_SHORT).show()
                setResult(1, i)
                finish()
            } else {
                Toast.makeText(applicationContext, "Delete Error", Toast.LENGTH_SHORT).show()
                setResult(0, i)
                finish()
            }

        }

        binding.imageContact.setOnClickListener {
            if (binding.layoutEditDelete.isVisible) {
                launcher.launch(Intent(applicationContext, ContactImageSelectionActivity::class.java))
            }
        }

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.data != null && it.resultCode == 1) {
                imageId = it.data?.extras?.getInt("id")
                binding.imageContact.setImageResource(imageId!!)
            } else {
                imageId = -1
                binding.imageContact.setImageResource(R.drawable.profiledefault)
            }
        }

    }

    private fun changeEditText(status: Boolean) {
        binding.editName.isEnabled = status
        binding.editAddress.isEnabled = status
        binding.editPhone.isEnabled = status
        binding.editEmail.isEnabled = status
    }

    private fun populate() {

        binding.editName.setText(contactModel.name)
        binding.editAddress.setText(contactModel.address)
        binding.editEmail.setText(contactModel.email)
        binding.editPhone.setText(contactModel.phone.toString())

        if (contactModel.imageId > 0) {
            binding.imageContact.setImageResource(contactModel.imageId)
        } else {
            binding.imageContact.setImageResource(R.drawable.profiledefault)
        }
    }
}