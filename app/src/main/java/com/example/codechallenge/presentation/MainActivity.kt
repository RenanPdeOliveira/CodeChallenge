package com.example.codechallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.codechallenge.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavView = findViewById(R.id.bottomNavigationView)

        val homeFragment = HomeFragment.newInstance()
        val graphicFragment = GraphicFragment.newInstance()

        onCreateFragment(homeFragment)

        bottomNavView.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.homeButtonMenu -> {
                    showFragment(homeFragment)
                    true
                }

                R.id.graphicButtonMenu -> {
                    showFragment(graphicFragment)
                    true
                }

                else -> false
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainerView, fragment)
            addToBackStack(null)
        }
    }

    private fun onCreateFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainerView, fragment)
        }
    }

}