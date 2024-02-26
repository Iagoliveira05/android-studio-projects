package com.example.recyclerviewlistadecompas.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewlistadecompas.adapter.ProdutoListAdapter
import com.example.recyclerviewlistadecompas.data.ProdutoMock
import com.example.recyclerviewlistadecompas.databinding.ActivityMainBinding
import com.example.recyclerviewlistadecompas.model.Produto

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProdutoListAdapter
    private lateinit var mock: ProdutoMock
    private var pos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        mock = ProdutoMock()
        adapter = ProdutoListAdapter(mock.listaProdutos, ProdutoListAdapter.OnClickListener {produto ->
            pos = pesquisaPosicao(produto.id)
            binding.editProduto.setText(mock.listaProdutos[pos].nome)
        })

        binding.recyclerView.adapter = adapter

        binding.buttonInserir.setOnClickListener {
            val produto = binding.editProduto.text.toString()
            mock.listaProdutos.add(Produto((0..100).random(), produto))
            pos = -1
            adapter.notifyDataSetChanged()

            binding.editProduto.setText("")
        }

        binding.buttonEditar.setOnClickListener {
            if (pos >= 0) {
                val produto = binding.editProduto.text.toString()
                mock.listaProdutos[pos].id = (0..100).random()

                mock.listaProdutos[pos].nome = produto

                adapter.notifyDataSetChanged()

            }
        }

        binding.buttonDeletar.setOnClickListener {
            if (pos >= 0) {
                mock.listaProdutos.removeAt(pos)
                pos = -1
                adapter.notifyDataSetChanged()

                binding.editProduto.setText("")
            }
        }
    }

    private fun pesquisaPosicao(id: Int): Int {
        for (i in 0 .. mock.listaProdutos.size) {
            if (mock.listaProdutos[i].id == id) {
                return i
            }
        }
        return -1
    }
}