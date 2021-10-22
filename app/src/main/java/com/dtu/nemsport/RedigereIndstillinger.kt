package com.dtu.nemsport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class RedigereIndstillinger : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redigere_indstillinger)

        val actionBar = supportActionBar
        actionBar!!.title = "Rediger Indstillinger"
        actionBar.setDisplayHomeAsUpEnabled(true)

        val nyGemKnap: Button = findViewById(R.id.gemKnap)

        val rigtigNavn: TextView = findViewById(R.id.navn)
        val nyNavn: EditText = findViewById(R.id.edtxNavn)

        val rigtigEmail: TextView = findViewById(R.id.email)
        val nyEmail: EditText = findViewById(R.id.edtxEmail)

        val rigtigAdresse: TextView = findViewById(R.id.adresse)
        val nyAdresse: EditText = findViewById(R.id.edtxAdresse)

        val rigtigNummer: TextView = findViewById(R.id.nummer)
        val nyNummer: EditText = findViewById(R.id.edtxNummer)

        nyGemKnap.setOnClickListener {
            val navnBesked: String = nyNavn.getText().toString()
            val emailBesked: String = nyEmail.getText().toString()
            val adresseBesked: String = nyAdresse.getText().toString()
            val nummerBesked: String = nyNummer.getText().toString()

            rigtigNavn.setText(navnBesked)
            rigtigEmail.setText(emailBesked)
            rigtigAdresse.setText(adresseBesked)
            rigtigNummer.setText(nummerBesked)

        }

    }
}