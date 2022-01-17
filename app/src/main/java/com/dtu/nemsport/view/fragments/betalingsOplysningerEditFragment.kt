package com.dtu.nemsport.view.fragments

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.nfc.Tag
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import com.dtu.nemsport.R
import com.dtu.nemsport.models.FakeDB
import com.google.firebase.firestore.FirebaseFirestore


class betalingsOplysningerEditFragment : Fragment() {

    lateinit var nytKortNummer: EditText
    lateinit var nytMM: EditText
    lateinit var nytYY: EditText
    lateinit var nytCVV: EditText
    lateinit var gemButton: Button
    private lateinit var db: FirebaseFirestore

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

        gemButton = view.findViewById(R.id.gemButton)
        gemButton.setOnClickListener {

            val updateKortNummer = nytKortNummer.text.toString()
            val updateMM = nytMM.text.toString()
            val updateYY = nytYY.text.toString()
            val updateCVV = nytCVV.text.toString()


            // Update in the databasecall
            db = FirebaseFirestore.getInstance()

            val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
            val uidValue = sharedPref.getString("nemsport_uid", "")

            if(TextUtils.isEmpty(updateKortNummer) || TextUtils.isEmpty(updateMM) || TextUtils.isEmpty(updateYY)
                || TextUtils.isEmpty(updateCVV)){
                Toast.makeText(context,"Indtast venligst et gyldigt input",Toast.LENGTH_LONG).show()
            } else {

                /*fakeDB.kortNummberData[0].kortNummer = updateKortNummer
               fakeDB.kortNummberData[0].MM = updateMM
               fakeDB.kortNummberData[0].YY = updateYY
               fakeDB.kortNummberData[0].CVV = updateCVV */

                // Update kortnummer
                val kortNummerUpdate = db.collection("users").document(uidValue.toString())
                kortNummerUpdate
                    .update("Card number",updateKortNummer)
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "card number successfully updated!") }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error updating document", e) }

                // Update Måneder
                val mmUpdate = db.collection("users").document(uidValue.toString())
                kortNummerUpdate
                    .update("MM",mmUpdate)
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "MM successfully updated!") }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error updating document", e) }

                // Update År
                val yyUpdate = db.collection("users").document(uidValue.toString())
                kortNummerUpdate
                    .update("YY",yyUpdate)
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "YY successfully updated!") }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error updating document", e) }

                // Update CVV
                val cvvUpdate = db.collection("users").document(uidValue.toString())
                kortNummerUpdate
                    .update("CVV",cvvUpdate)
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "CVV successfully updated!") }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error updating document", e) }

            }
            Navigation.findNavController(view).navigate(R.id.editBetalingToBetalingOplysning)
            Toast.makeText(context, "Kommet til Betalingsoplysninger", Toast.LENGTH_SHORT).show()

        }

        val tilbageButton: Button = view.findViewById(R.id.tilbageButton)
        tilbageButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.betalingToIndstilling)

        }

    }



}