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
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.dtu.nemsport.R
import com.dtu.nemsport.models.FakeDB


class betalingsOplysningerEditFragment : Fragment() {

    lateinit var kortnummerEditview: EditText
    lateinit var MM: EditText
    lateinit var YY: EditText
    lateinit var CVV: EditText
    lateinit var gemButton: Button
    lateinit var KortnummerTextview: TextView
    lateinit var nytKortNummer: EditText

    var fakeDB = FakeDB



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_betalings_editoplysninger, container, false)

        nytKortNummer = view.findViewById(R.id.kortnummerEditview)
        nytKortNummer.setText(FakeDB.kortNummberData[0].kortNummer)

        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val gemButton: Button = view.findViewById(R.id.gemButton)
        gemButton.setOnClickListener {

            val updateKortNummer = nytKortNummer.text.toString()


            fakeDB.kortNummberData[0].kortNummer = updateKortNummer

            Navigation.findNavController(view).navigate(R.id.editBetalingToBetalingOplysning)



        }

        val tilbageButton: Button = view.findViewById(R.id.tilbageButton)
        tilbageButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.betalingToIndstilling)

        }

    }



}