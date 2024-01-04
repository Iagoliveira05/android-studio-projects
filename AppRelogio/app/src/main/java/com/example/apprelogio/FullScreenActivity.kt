package com.example.apprelogio

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apprelogio.databinding.ActivityFullscreenBinding

class FullScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = this.getSharedPreferences("check_box", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        var isChecked = sharedPreference.getBoolean("isChecked", true)
        binding.checkNivelBateria.isChecked = isChecked

        if (isChecked) {
            binding.textNivelBateria.visibility = View.VISIBLE
        } else {
            binding.textNivelBateria.visibility = View.GONE
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val bateriaReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent != null) {
                    val nivel : Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
                    binding.textNivelBateria.setText("${nivel}%")
                }
            }
        }

        registerReceiver(bateriaReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        binding.checkNivelBateria.setOnClickListener {
            if (isChecked) {
                editor.clear()
                editor.putBoolean("isChecked", false)
                binding.textNivelBateria.visibility = View.GONE
                Toast.makeText(applicationContext, "Porcentagem Desativada", Toast.LENGTH_SHORT).show()
            } else {
                editor.clear()
                editor.putBoolean("isChecked", true)
                binding.textNivelBateria.visibility = View.VISIBLE
                Toast.makeText(applicationContext, "Porcentagem Ativada", Toast.LENGTH_SHORT).show()
            }
            editor.apply()
            isChecked = sharedPreference.getBoolean("isChecked", true)
            binding.checkNivelBateria.isActivated = isChecked
        }

        binding.layoutMenu.animate().translationY(500F)

        binding.imageViewPrefencias.setOnClickListener {
            binding.layoutMenu.visibility = View.VISIBLE
            binding.layoutMenu.animate().translationY(0F).setDuration(
                resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
            )
        }

        binding.imageViewFechar.setOnClickListener {
            binding.layoutMenu.animate().translationY(binding.layoutMenu.measuredHeight.toFloat())
                .setDuration(
                    resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
            )
        }

    }
}






















