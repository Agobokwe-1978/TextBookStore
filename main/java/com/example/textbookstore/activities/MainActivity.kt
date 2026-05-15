package com.example.textbookstore.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.textbookstore.R
import com.example.textbookstore.databinding.ActivityMainBinding
import com.example.textbookstore.fragments.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()

        // Default fragment
        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.nav_home
        }
    }

    private fun setupBottomNavigation() {

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->

            val selectedFragment: Fragment = when (menuItem.itemId) {

                R.id.nav_home -> HomeFragment()

                R.id.nav_listings -> ListingsFragment()

                R.id.nav_inbox -> InboxFragment()

                R.id.nav_profile -> ProfileFragment()

                else -> HomeFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit()

            true
        }
    }
}