package com.example.ciclovidaactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ciclovidaactivity.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonVoltar.setOnClickListener {
            finish()
        }
    }


    override fun onStart() {  // Acontece após onCreate
        super.onStart()
    }

    override fun onResume() { // Acontece após onStart e significa já estar pronta a tela
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