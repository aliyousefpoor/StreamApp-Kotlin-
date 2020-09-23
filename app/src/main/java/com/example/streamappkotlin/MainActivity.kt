package com.example.streamappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navigation: BottomNavigationView
    private lateinit var moreNavHost: View
    private lateinit var homeNavHost: View
    private lateinit var categoryNavHost: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        navigation = findViewById(R.id.bottomNavigation)
        homeNavHost = findViewById(R.id.navHostHomeFragment)
        moreNavHost = findViewById(R.id.navHostMoreFragment)
        categoryNavHost = findViewById(R.id.navHostCategoryFragment)

        moreNavHost.visibility = View.GONE
        categoryNavHost.visibility = View.GONE
        navigation.selectedItemId = R.id.homeFragment

        navController = Navigation.findNavController(this, R.id.navHostHomeFragment)

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navController = Navigation.findNavController(this, R.id.navHostHomeFragment)
                    homeNavHost.visibility = View.VISIBLE
                    categoryNavHost.visibility = View.GONE
                    moreNavHost.visibility = View.GONE
                    true
                }

                R.id.categoryFragment -> {
                    navController = Navigation.findNavController(this, R.id.navHostCategoryFragment)
                    categoryNavHost.visibility = View.VISIBLE
                    moreNavHost.visibility = View.GONE
                    homeNavHost.visibility = View.GONE
                    true
                }

                R.id.moreFragment -> {
                    navController = Navigation.findNavController(this, R.id.navHostMoreFragment)
                    moreNavHost.visibility = View.VISIBLE
                    categoryNavHost.visibility = View.GONE
                    homeNavHost.visibility = View.GONE
                    true
                }
                else -> {
                    false
                }


            }

        }
    }

    override fun onBackPressed() {
        if (!navController.navigateUp()) {
            super.onBackPressed()
        }
    }
}


