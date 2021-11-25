package com.dtu.nemsport.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import com.dtu.nemsport.*

import com.dtu.nemsport.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*
        Forside screen is going to be here in MainActivity

         */


        /*val knapTilLogin: Button = findViewById(R.id.buttonLogin)
        val knapTilOpret: Button = findViewById(R.id.buttonOpretProfil)

        knapTilLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        knapTilOpret.setOnClickListener {
            val intent = Intent(this, OpretProfil::class.java)
            startActivity(intent)
        }*/



        


        // val intent = Intent(this, OpretProfilAktivitet::class.java)
        // startActivity(intent)

        // /*val knap: Button = findViewById(R.id.button)
        // knap.setOnClickListener {


        // }*/


    }

}