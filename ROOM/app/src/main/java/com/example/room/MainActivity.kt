package com.example.room

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = UserAdapter()
    private var id = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.recyclerUsers.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerUsers.adapter = adapter

        val listener = object : OnUserListener {
            override fun OnClick(id: Int) {
                Toast.makeText(applicationContext, id.toString(), Toast.LENGTH_SHORT).show()
                viewModel.get(id)
            }
        }

        adapter.attachListener(listener)

        binding.buttonInsert.setOnClickListener {
            val username = binding.editUsername.text.toString()
            val password = binding.editPassword.text.toString()

            viewModel.insert(username, password)
        }

        binding.buttonUpdate.setOnClickListener {
            val username = binding.editUsername.text.toString()
            val password = binding.editPassword.text.toString()

            viewModel.update(id, username, password)
        }

        binding.buttonDelete.setOnClickListener {
            viewModel.delete(id)
        }

        observe()
        viewModel.getAll()
    }

    private fun observe() {
        viewModel.users.observe(this) {
            adapter.updateUsers(it)
        }

        viewModel.user.observe(this) {
            id = it.id
            binding.textId.setText(id.toString())
            binding.editUsername.setText(it.username)
            binding.editPassword.setText(it.password)
        }

        viewModel.newChange.observe(this) {
            viewModel.getAll()
        }
    }
}