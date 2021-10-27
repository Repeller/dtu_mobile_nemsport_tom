package com.dtu.nemsport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OpretProfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opret_profil)


        val knapTilMainPage: Button = findViewById(R.id.button)
        knapTilMainPage.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)
        }


    }
}