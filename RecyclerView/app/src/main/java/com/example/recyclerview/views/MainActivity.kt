package com.example.recyclerview.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.adapter.CarroListAdapter
import com.example.recyclerview.data.CarroMock
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.model.Carro
import kotlin.random.Random

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

        adapter = CarroListAdapter(mock.listaCarros, CarroListAdapter.OnClickListener { carro ->
            //Toast.makeText(this, carro.modelo, Toast.LENGTH_SHORT).show()
            pos = pesquisaPosicao(carro.id)
            binding.editModelo.setText(mock.listaCarros[pos].modelo)
        })
        binding.recyclerView.adapter = adapter


        binding.buttonInserir.setOnClickListener {
            val modelo = binding.editModelo.text.toString()
            if (modelo.isNotEmpty()) {
                mock.listaCarros.add(Carro(gerarId(), modelo))

                adapter.notifyDataSetChanged()
                binding.editModelo.setText("")
                pos = -1
            }
        }

        binding.buttonEditar.setOnClickListener {
            if (pos >= 0) {
                val modelo = binding.editModelo.text.toString()
                mock.listaCarros[pos].modelo = modelo
                mock.listaCarros[pos].id = mock.listaCarros[pos].id

                pos = -1
                adapter.notifyDataSetChanged()
                binding.editModelo.setText("")
            }
        }
        binding.buttonEliminar.setOnClickListener {
            if (pos >= 0) {
                mock.listaCarros.removeAt(pos)
                pos = -1
                adapter.notifyDataSetChanged()
                binding.editModelo.setText("")
            }
        }
    }

    private fun gerarId() : Int {
        return Random.nextInt(5, 20)
    }

    private fun pesquisaPosicao(id: Int): Int {
        for (i in 0..mock.listaCarros.size) {
            if (mock.listaCarros[i].id == id) {
                return i
            }
        }
        return  -1
    }
}