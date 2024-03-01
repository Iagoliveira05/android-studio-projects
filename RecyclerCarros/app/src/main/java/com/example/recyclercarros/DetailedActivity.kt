package com.example.recyclercarros

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclercarros.databinding.ActivityDetailedBinding
import java.text.NumberFormat
import java.util.Locale

class DetailedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val car = intent.getParcelableExtra<Car>("car")

        if (car != null) {
            binding.imageCarDetailed.setImageResource(car.image)
            binding.textModelDetailed.text = car.model

            val formatCurrency = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            binding.textPriceDetailed.text = formatCurrency.format(car.price)
        }
    }
}