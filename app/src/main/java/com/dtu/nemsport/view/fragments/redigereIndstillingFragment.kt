package com.dtu.nemsport.view.fragments

import android.content.ContentValues.TAG
import android.content.Context
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
import com.dtu.nemsport.models.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.runBlocking


class RedigereIndstilling : Fragment() {

    lateinit var nyNavn: EditText
    lateinit var nyEmail: TextView
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


        return view

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gemKnap = view.findViewById(R.id.nyGemKnap)

        runBlocking {
            getData()
        }

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

            runBlocking {
                setData()
            }

        }


    }

    suspend fun getData() {
        db = FirebaseFirestore.getInstance()

        // 00 - get user id
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        var uid = sharedPref.getString("nemsport_uid", "")

        if (uid != null) {
            Log.w("uid - profil - onViewCreated:", uid)
        }
        // 00 - get the values from the DB
        if (uid != null) {

            val navn = requireView().findViewById<TextView>(R.id.edtxNavn)
            val bigNavnProfil = requireView().findViewById<TextView>(R.id.bigNavn)
            val nyEmail = requireView().findViewById<TextView>(R.id.edtxEmail)
            val nyAdresse = requireView().findViewById<TextView>(R.id.edtxAdresse)
            val nyNummer = requireView().findViewById<TextView>(R.id.edtxNummer)

            // get the current data from the database
            db.collection("users").document(uid)
                .get()
                .addOnSuccessListener { doc ->


                    navn.text = doc.getString("name").toString()
                    bigNavnProfil.text = doc.getString("name").toString()
                    nyEmail.text = doc.getString("mail").toString()
                    nyAdresse.text = doc.getString("address").toString()
                    nyNummer.text = doc.getString("phone").toString()

                }
                .addOnFailureListener {
                    // TODO: maybe we need to do it some other way than this xDDDDD

                }
        }


    }

    suspend fun setData(){
        db = FirebaseFirestore.getInstance()



        // 00 - get user id
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        var uid = sharedPref.getString("nemsport_uid", "")

        if (uid != null) {
            Log.w("uid - profil - onViewCreated:", uid)
        }
        // 00 - get the values from the DB
        if (uid != null) {

            // get the current data from the database
                    val navn = requireView().findViewById<TextView>(R.id.edtxNavn)
                    val bigNavnProfil = requireView().findViewById<TextView>(R.id.bigNavn)
                    val nyEmail = requireView().findViewById<TextView>(R.id.edtxEmail)
                    val nyAdresse = requireView().findViewById<TextView>(R.id.edtxAdresse)
                    val nyNummer = requireView().findViewById<TextView>(R.id.edtxNummer)


            var newUser : User = User(nyEmail.text.toString(), navn.text.toString(), nyNummer.text.toString(), nyAdresse.text.toString(), true)

                    // save the edited
                    db.collection("users").document(uid).set(newUser)
                        .addOnSuccessListener { Toast.makeText(context, "text works", Toast.LENGTH_SHORT).show()  }
                        .addOnFailureListener { Toast.makeText(context, "text does not work", Toast.LENGTH_SHORT).show()  }
                }

        }

}