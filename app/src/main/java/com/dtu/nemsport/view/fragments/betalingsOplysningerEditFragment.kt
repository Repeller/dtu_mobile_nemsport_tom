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

    lateinit var nytKortNummer: EditText
    lateinit var nytMM: EditText
    lateinit var nytYY: EditText
    lateinit var nytCVV: EditText

    var fakeDB = FakeDB



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_betalings_editoplysninger, container, false)

        nytKortNummer = view.findViewById(R.id.kortnummerEditview)
        nytMM = view.findViewById(R.id.MM)
        nytYY = view.findViewById(R.id.YY)
        nytCVV = view.findViewById(R.id.CVV)


        nytKortNummer.setText(FakeDB.kortNummberData[0].kortNummer)
        nytMM.setText(FakeDB.kortNummberData[0].MM)
        nytYY.setText(FakeDB.kortNummberData[0].YY)
        nytCVV.setText(FakeDB.kortNummberData[0].CVV)


        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val gemButton: Button = view.findViewById(R.id.gemButton)
        gemButton.setOnClickListener {

            val updateKortNummer = nytKortNummer.text.toString()
            val updateMM = nytMM.text.toString()
            val updateYY = nytYY.text.toString()
            val updateCVV = nytCVV.text.toString()

            fakeDB.kortNummberData[0].kortNummer = updateKortNummer
            fakeDB.kortNummberData[0].MM = updateMM
            fakeDB.kortNummberData[0].YY = updateYY
            fakeDB.kortNummberData[0].CVV = updateCVV

            Navigation.findNavController(view).navigate(R.id.editBetalingToBetalingOplysning)



        }

        val tilbageButton: Button = view.findViewById(R.id.tilbageButton)
        tilbageButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.betalingToIndstilling)

        }

    }



}