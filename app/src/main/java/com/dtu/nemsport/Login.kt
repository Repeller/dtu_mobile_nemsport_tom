package com.dtu.nemsport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val knapTilMainPage: Button = findViewById(R.id.buttonLogin)
        val knapTilGlemtKode: Button = findViewById(R.id.buttonForgotPassword)

        knapTilMainPage.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)
        }

        knapTilGlemtKode.setOnClickListener {
            val intent = Intent(this, GlemtKode::class.java)
            startActivity(intent)
        }

    }
}