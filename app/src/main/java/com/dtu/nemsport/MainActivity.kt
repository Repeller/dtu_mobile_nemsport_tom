package com.example.bottomnavbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dtu.nemsport.R
import com.dtu.nemsport.RedigereIndstillinger
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Navigation view
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Navigation controller
        val navController = findNavController(R.id.fragmentContainerView)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.profilFragment,R.id.aktiviteterFragment,R.id.banerFragment,R.id.indstillingerFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

        // Go to skiftIndstillinger activity
        val knapIndstillinger: Button = findViewById(R.id.skiftIndstillinger)
        knapIndstillinger.setOnClickListener {
            val intent = Intent(this,RedigereIndstillinger::class.java)
            startActivity(intent)
        }

    }
}