package com.dtu.nemsport.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dtu.nemsport.R
import com.dtu.nemsport.view.fragments.RedigereIndstilling
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        // Navigation view
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Navigation controller
        val navController = findNavController(R.id.fragmentContainerView)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.profilFragment,
            R.id.aktiviteterFragment,
            R.id.banerFragment,
            R.id.indstillingerFragment
        ))
        setupActionBarWithNavController(navController,appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)




    }


}