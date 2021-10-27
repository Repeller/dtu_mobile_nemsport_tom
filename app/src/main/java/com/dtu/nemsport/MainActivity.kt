package com.example.bottomnavbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.*
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dtu.nemsport.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        Forside screen is going to be here in MainActivity

         */


        val knapTilLogin: Button = findViewById(R.id.buttonLogin)
        val knapTilOpret: Button = findViewById(R.id.buttonOpretProfil)

        knapTilLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        knapTilOpret.setOnClickListener {
            val intent = Intent(this, OpretProfil::class.java)
            startActivity(intent)
        }





    }

}