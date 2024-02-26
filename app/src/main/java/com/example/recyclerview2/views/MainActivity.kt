package com.example.recyclerview2.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview2.adapter.CarroListAdapter
import com.example.recyclerview2.data.CarroMock
import com.example.recyclerview2.databinding.ActivityMainBinding
import com.example.recyclerview2.model.Carro

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CarroListAdapter
    private lateinit var mock: CarroMock
    private var pos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        mock = CarroMock()
        adapter = CarroListAdapter(mock.listaCarros, CarroListAdapter.OnClickListener {carro ->
            //Toast.makeText(this, carro.modelo, Toast.LENGTH_SHORT).show()
            pos = pesquisaPosicao(carro.id)
            binding.editModelo.setText(mock.listaCarros[pos].modelo)
        })
        binding.recyclerView.adapter = adapter

        binding.buttonInserir.setOnClickListener {
            val modelo = binding.editModelo.text.toString().toInt()
            mock.listaCarros.add(Carro(modelo, modelo.toString()))
            pos = -1


            adapter.notifyDataSetChanged()
        }

        binding.buttonEditar.setOnClickListener {
            if (pos >= 0) {
                val modelo = binding.editModelo.text.toString()
                mock.listaCarros[pos].id = modelo.toInt()
                mock.listaCarros[pos].modelo = modelo
                pos = -1

                adapter.notifyDataSetChanged()
            }
        }

        binding.buttonEliminar.setOnClickListener {
            if (pos >= 0) {
                mock.listaCarros.removeAt(pos)
                pos = -1

                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun pesquisaPosicao(id: Int): Int {
        for (i in 0..mock.listaCarros.size) {
            if (mock.listaCarros[i].id == id) {
                return i
            }
        }
        return -1
    }

}