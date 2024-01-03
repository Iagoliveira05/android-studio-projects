package com.example.sharedpreference

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sharedpreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = this.getSharedPreferences("valores", Context.MODE_PRIVATE)
        val valor = sharedPreference.getString("valor", "")

        binding.textValor.setText(valor)

        binding.buttonGarvar.setOnClickListener {
            val novoValor = binding.editValor.text.toString()
            binding.textValor.setText(novoValor)

            val editor = sharedPreference.edit()
            editor.putString("valor", novoValor)
            editor.apply()
        }
    }
}