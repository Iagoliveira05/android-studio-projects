package com.example.ciclovidaactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ciclovidaactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPrimeira.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }
    override fun onStart() {  // Acontece após onCreate()
        super.onStart()
    }

    override fun onResume() { // Acontece após onStart() e significa já estar pronta a tela
        super.onResume()
    }

    override fun onPause() {    // Quando outra janela está aberta ou uma chamada
        super.onPause()
    }

    override fun onStop() {     // Para a activity
        super.onStop()
    }

    override fun onDestroy() {  // Quando ocorre o finish()
        super.onDestroy()
    }
}