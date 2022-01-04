package com.dtu.nemsport.view.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.dtu.nemsport.R
import com.dtu.nemsport.models.FakeDB

class betalingsOplysninger : Fragment() {

    lateinit var preferences: SharedPreferences
    lateinit var kortnummerText: TextView
    lateinit var MM: TextView
    lateinit var YY: TextView
    lateinit var CVV: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_betalings_oplysninger, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nyKortNummer = view.findViewById<TextView>(R.id.kortnummerText)

        nyKortNummer.text= FakeDB.kortNummberData[0].kortNummer

        val tilbageButton: Button = view.findViewById(R.id.tilbageButton)
        tilbageButton.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.betalingOplysningerToIndstillinger)
        }

    }


}