package com.example.listatelefonica.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listatelefonica.R
import com.example.listatelefonica.database.DBHelper
import com.example.listatelefonica.databinding.ActivityNewContactBinding

class NewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val db = DBHelper(applicationContext)
        val i = intent

        binding.buttonSave.setOnClickListener {
            val name = binding.editName.text.toString()
            val address = binding.editAddress.text.toString()
            val email = binding.editEmail.text.toString()
            val phone = binding.editPhone.text.toString()
            val imageId = 1

            if (name.isNotEmpty() && address.isNotEmpty() && email.isNotEmpty()) {
                val res = db.insertContact(name, address, email, phone.toInt(), imageId)

                if (res > 0) {
                    Toast.makeText(applicationContext, "Insert OK", Toast.LENGTH_SHORT).show()
                    setResult(1, i)
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Insert Error", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, getString(R.string.please_insert_all_requires_field), Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonCancel.setOnClickListener {
            setResult(0, i)
            finish()
        }
    }
}