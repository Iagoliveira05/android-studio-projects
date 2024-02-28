package com.example.dbsqliterevisao.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dbsqliterevisao.adapter.UsuariosListAdapter
import com.example.dbsqliterevisao.data.DBHelper
import com.example.dbsqliterevisao.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UsuariosListAdapter
    private lateinit var db: DBHelper
    private var id: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        db = DBHelper(this)


        db.usuariosSelectAll().forEach {
            db.listaUsuarios.add(it)
        }

        adapter = UsuariosListAdapter(db.listaUsuarios, UsuariosListAdapter.OnClickListener {usuario ->
            binding.editNome.setText(db.getNomeById(usuario.id))
            id = usuario.id
        })
        binding.recyclerView.adapter = adapter


        binding.buttonInserir.setOnClickListener {
            val nome = binding.editNome.text.toString()
            if (nome.isNotEmpty()) {
                val res = db.insertNome(nome)

                if (res >= 0) {
                    Toast.makeText(applicationContext, "Insert Ok: $res", Toast.LENGTH_SHORT).show()
                    adapter.notifyDataSetChanged()
                    binding.editNome.setText("")
                }
                else {
                    Toast.makeText(applicationContext, "Insert ERROR: $res", Toast.LENGTH_SHORT).show()
                }
            }
            id = -1
        }

        binding.buttonDeletar.setOnClickListener {
            if (id >= 0) {
                db.deleteNome(id)
                id = -1
                adapter.notifyDataSetChanged()
                binding.editNome.setText("")
            } else {
                Toast.makeText(applicationContext, "Selecione uma informação", Toast.LENGTH_SHORT).show()
            }
        }

    }
}