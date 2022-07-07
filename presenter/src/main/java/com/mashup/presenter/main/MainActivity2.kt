package com.mashup.presenter.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.mashup.presenter.R
import com.mashup.presenter.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        //binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.homeFragment -> {
                        navController.navigate(R.id.homeFragment)
                        true
                    }
                    R.id.profileFragment -> {
                        navController.navigate(R.id.profileFragment)
                        true
                    }
                    R.id.settingFragment -> {
                        navController.navigate(R.id.settingFragment)
                        true
                    }
                    else -> false
                }

            }

        }
    }
}