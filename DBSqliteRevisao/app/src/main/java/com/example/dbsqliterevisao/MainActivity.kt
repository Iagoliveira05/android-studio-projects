package com.example.dbsqliterevisao

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dbsqliterevisao.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<Nomes>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(this)

        val listaNomes = db.nomesSelectAll()

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaNomes)
        binding.listView.adapter = adapter


        binding.buttonInserir.setOnClickListener {
            val nome = binding.editNome.text.toString()

            val res = db.insertNome(nome)

            if (res >= 0) {
                Toast.makeText(applicationContext, "Insert Ok: $res", Toast.LENGTH_SHORT).show()
                listaNomes.add(Nomes(res.toInt(), nome))
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "Insert Erro: $res", Toast.LENGTH_SHORT).show()
            }
        }

    }
}