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
import com.dtu.nemsport.MainPage
import com.dtu.nemsport.R
import com.dtu.nemsport.RedigereIndstilling
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        Login screen is going to be here in MainActivity

         */


        val knapTilMainPage: Button = findViewById(R.id.knapTilMainPage)
        knapTilMainPage.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)
        }




    }

}