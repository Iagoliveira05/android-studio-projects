package com.example.bottomnavigationapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bottomnavigationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragmnet(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> replaceFragmnet(HomeFragment())
                R.id.item_account -> replaceFragmnet(AccountFragment())
                R.id.item_search -> replaceFragmnet(SearchFragment())
                R.id.item_logout -> finish()
                else -> {
                    replaceFragmnet(HomeFragment())
                }
            }
            true
        }
    }

    private fun replaceFragmnet(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmenteTransaction = fragmentManager.beginTransaction()
        fragmenteTransaction.replace(R.id.frame_layout, fragment)
        fragmenteTransaction.commit()

    }
}