package com.example.bundleextras

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bundleextras.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent

        val bundle = i.extras

        binding.buttonOk.setOnClickListener {
            val numero = binding.editNumero.text.toString().trim().toInt()

            val i = Intent(this, MainActivity3::class.java)
            if (bundle != null) {
                i.putExtras(bundle)
            }
            i.putExtra("numero2", numero)

            startActivity(i)
        }
    }
}