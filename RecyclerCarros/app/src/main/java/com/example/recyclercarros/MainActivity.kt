package com.example.recyclercarros

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclercarros.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var carAdapter: CarAdapter
    lateinit var carList: ArrayList<Car>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        addDefaultData()

        carAdapter = CarAdapter(carList)
        binding.recyclerView.adapter = carAdapter

        carAdapter.onItemClick = {
            val intent = Intent(this, DetailedActivity::class.java)
            intent.putExtra("car", it)
            startActivity(intent)
        }

    }

    private fun addDefaultData() {
        carList = ArrayList()
        // id, image, model, price
        carList.add(Car(1, R.drawable.audi, "Audi", 610133.90))
        carList.add(Car(3, R.drawable.camaro, "Camaro", 694356.32))
        carList.add(Car(4, R.drawable.fiat, "Fiat", 470553.54))
        carList.add(Car(5, R.drawable.versa, "Versa", 7637096.43))
        carList.add(Car(6, R.drawable.golf, "Golf", 663299.82))
        carList.add(Car(7, R.drawable.mercedes, "Mercedes", 756339.65))

        carList.add(Car(1, R.drawable.audi, "Audi", 610133.90))
        carList.add(Car(3, R.drawable.camaro, "Camaro", 694356.32))
        carList.add(Car(4, R.drawable.fiat, "Fiat", 470553.54))
        carList.add(Car(5, R.drawable.versa, "Versa", 7637096.43))
        carList.add(Car(6, R.drawable.golf, "Golf", 663299.82))
        carList.add(Car(7, R.drawable.mercedes, "Mercedes", 756339.65))
    }
}