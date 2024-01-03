package com.example.audio

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaPlayer = MediaPlayer.create(applicationContext, R.raw.intro)
        mediaPlayer.start()
    }
}