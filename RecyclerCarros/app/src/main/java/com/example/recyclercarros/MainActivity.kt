package com.example.recyclercarros

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclercarros.databinding.ActivityMainBinding
import java.util.Locale

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


        binding.searchViewCaro.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<Car>()
            for (i in carList) {
                if (i.model?.lowercase(Locale.ROOT)!!.contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(applicationContext, "Nenhum carro encontrado", Toast.LENGTH_SHORT).show()
            } else {
                carAdapter.setFilteredList(filteredList)
            }
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