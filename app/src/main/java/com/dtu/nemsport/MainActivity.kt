package com.example.bottomnavbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dtu.nemsport.OpretProfilAktivitet
import com.dtu.nemsport.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        


        val intent = Intent(this, OpretProfilAktivitet::class.java)
        startActivity(intent)

        /*val knap: Button = findViewById(R.id.button)
        knap.setOnClickListener {


        }*/


        // Navigation view
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Navigation controller
        val navController = findNavController(R.id.fragmentContainerView)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.profilFragment,R.id.aktiviteterFragment,R.id.banerFragment,R.id.indstillingerFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

    }
}