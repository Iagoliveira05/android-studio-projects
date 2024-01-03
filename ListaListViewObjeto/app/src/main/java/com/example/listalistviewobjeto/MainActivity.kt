package com.example.listalistviewobjeto

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.listalistviewobjeto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var pos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listaUtilizadores = ArrayList<Utilizadores>()
        listaUtilizadores.add(Utilizadores("user", "pass"))
        listaUtilizadores.add(Utilizadores("admin", "pwd123"))
        listaUtilizadores.add(Utilizadores("aaa", "bbb"))

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUtilizadores)
        binding.listViewUtilizadores.adapter = adapter

        binding.listViewUtilizadores.setOnItemClickListener { _, _, position, _ ->
            binding.editUsername.setText(listaUtilizadores.get(position).username)
            binding.editPassword.setText(listaUtilizadores.get(position).password)
            pos = position
        }

        binding.buttonInserir.setOnClickListener {
            val username = binding.editUsername.text.toString().trim()
            val password = binding.editPassword.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                listaUtilizadores.add(Utilizadores(username, password))
                adapter.notifyDataSetChanged()

                binding.editUsername.setText("")
                binding.editPassword.setText("")
                pos = -1
            }
        }

        binding.buttonEditar.setOnClickListener {
            if (pos >= 0) {
                val username = binding.editUsername.text.toString().trim()
                val password = binding.editPassword.text.toString().trim()

                if (username.isNotEmpty() && password.isNotEmpty()) {
                    listaUtilizadores.get(pos).username = username
                    listaUtilizadores.get(pos).password = password

                    adapter.notifyDataSetChanged()
                    binding.editUsername.setText("")
                    binding.editPassword.setText("")
                    pos = -1
                }
            }
        }

        binding.buttonEliminar.setOnClickListener {
            if (pos >= 0) {
                listaUtilizadores.removeAt(pos)

                adapter.notifyDataSetChanged()
                binding.editUsername.setText("")
                binding.editPassword.setText("")
                pos = -1
            }
        }

        binding.buttonLimpar.setOnClickListener {
            listaUtilizadores.clear()
            adapter.notifyDataSetChanged()
            binding.editUsername.setText("")
            binding.editPassword.setText("")
            pos = -1

        }
    }
}