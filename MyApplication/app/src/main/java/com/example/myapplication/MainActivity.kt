package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOla.setOnClickListener {
            val nome = binding.editNome.text.toString().trim()
            val sobrenome = binding.editSobrenome.text.toString().trim()

            if (nome.isNotEmpty() && sobrenome.isNotEmpty()) {
                binding.textResultado.setText("Olá ${nome} ${sobrenome}!")
                Toast.makeText(applicationContext, "Olá ${nome} ${sobrenome}!", Toast.LENGTH_LONG).show()
            } else {
                binding.textResultado.setText("Preencha os campos acima!")
                Toast.makeText(applicationContext, "Preencha os campos acima!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}