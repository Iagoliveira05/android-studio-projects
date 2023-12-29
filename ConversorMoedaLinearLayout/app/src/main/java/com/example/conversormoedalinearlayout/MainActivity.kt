package com.example.conversormoedalinearlayout

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.conversormoedalinearlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDolar.setOnClickListener {
            Converter(0.8)
        }
        binding.buttonPeso.setOnClickListener {
            Converter(10.2)
        }
        binding.buttonReal.setOnClickListener {
            Converter(5.0)
        }
    }

    private fun Converter(taxa: Double) {
        val euros = binding.editEuros.text.toString()

        if (euros.isNotEmpty()) {
            val resultado = euros.toDouble() * taxa
            Toast.makeText(applicationContext, resultado.toString(), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Valor inválido!", Toast.LENGTH_SHORT).show()
        }
    }
}