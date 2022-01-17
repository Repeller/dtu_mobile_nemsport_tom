package com.dtu.nemsport.view.fragments

import android.content.ContentValues.TAG
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
import com.dtu.nemsport.models.FakeDB.db
import com.dtu.nemsport.models.FakeDB.user
import com.dtu.nemsport.models.FakeDB.userUID
import com.google.firebase.firestore.FirebaseFirestore


class RedigereIndstilling : Fragment() {

    lateinit var nyNavn: EditText
    lateinit var nyEmail: EditText
    lateinit var nyAdresse: EditText
    lateinit var nyNummer: EditText
    lateinit var gemKnap: Button
    private lateinit var db: FirebaseFirestore
    var fakeDB = FakeDB

    lateinit var bigNavn: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_redigere_indstilling, container, false)

        nyNavn = view.findViewById(R.id.edtxNavn)
        bigNavn = view.findViewById(R.id.bigNavn)
        nyEmail = view.findViewById(R.id.edtxEmail)
        nyAdresse = view.findViewById(R.id.edtxAdresse)
        nyNummer = view.findViewById(R.id.edtxNummer)

        // Set each variable as the text from the FakeDB
        nyNavn.setText(FakeDB.userData[0].navn)
        bigNavn.setText(FakeDB.userData[0].navn)
        nyEmail.setText(FakeDB.userData[0].email)
        nyAdresse.setText(FakeDB.userData[0].adresse)
        nyNummer.setText(FakeDB.userData[0].nummer)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gemKnap = view.findViewById(R.id.nyGemKnap)

        // Update each variable on button clicked and navigate to profilFragment
        gemKnap.setOnClickListener {

            val navn = nyNavn.text.toString()
            val email = nyEmail.text.toString()
            val adresse = nyAdresse.text.toString()
            val nummer = nyNummer.text.toString()
            val bigNavn = nyNavn.text.toString()

            // Test commit


            // Update in the databasecall
            db = FirebaseFirestore.getInstance()

            val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

            val uidValue = sharedPref.getString("nemsport_uid", "")
            val memStatus = sharedPref.getBoolean("medlemStatus", false).toString()


            if(TextUtils.isEmpty(navn) || TextUtils.isEmpty(email) || TextUtils.isEmpty(adresse) || TextUtils.isEmpty(nummer)) {
                Toast.makeText(context,"Indtast venligst et gyldigt input",Toast.LENGTH_LONG).show()
            } else {
                // Update each variable for what is on te editText

                /*fakeDB.userData[0].navn = navn
                fakeDB.userData[0].navn = bigNavn
                fakeDB.userData[0].email = email
                fakeDB.userData[0].adresse = adresse
                fakeDB.userData[0].nummer = nummer*/

                // Update navn
                val updateName = db.collection("users").document(uidValue.toString())
                updateName
                    .update("name", navn)
                    .addOnSuccessListener { Log.d(TAG, "Name successfully updated!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

                /*
                // Update email
                val updateEmail = db.collection("users").document(uidValue.toString())
                updateEmail
                    .update("mail", email)
                    .addOnSuccessListener { Log.d(TAG, "Name successfully updated!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) } */

                // Update adresse
                val updateAdresse = db.collection("users").document(uidValue.toString())
                updateAdresse
                    .update("address", adresse)
                    .addOnSuccessListener { Log.d(TAG, "Name successfully updated!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

                // Update phone number
                val updateNumber = db.collection("users").document(uidValue.toString())
                updateNumber
                    .update("phone", nummer)
                    .addOnSuccessListener { Log.d(TAG, "Name successfully updated!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

                Navigation.findNavController(view).navigate(R.id.redigereProfilToProfil)
                Toast.makeText(context, "Kommet til profil", Toast.LENGTH_SHORT).show()
            }


        }

    }


}