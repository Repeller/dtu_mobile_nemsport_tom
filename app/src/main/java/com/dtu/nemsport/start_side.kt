package com.dtu.nemsport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class start_side : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_side)

        val goToOpretProfilBTN = findViewById<Button>(R.id.buttonOpretProfil);
    }

}