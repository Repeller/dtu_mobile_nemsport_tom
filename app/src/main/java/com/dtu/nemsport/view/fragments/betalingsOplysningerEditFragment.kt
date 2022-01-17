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
import com.dtu.nemsport.models.User
import com.dtu.nemsport.models.kortNumberData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.runBlocking


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

        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gemButton = view.findViewById(R.id.gemButton)

        runBlocking {
            getData()
        }

        gemButton.setOnClickListener {

            val updateKortNummer = nytKortNummer.text.toString()
            val updateMM = nytMM.text.toString()
            val updateYY = nytYY.text.toString()
            val updateCVV = nytCVV.text.toString()


            // Update in the databasecall
            db = FirebaseFirestore.getInstance()

            val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
            val uidValue = sharedPref.getString("nemsport_uid", "")

            if (TextUtils.isEmpty(updateKortNummer) || TextUtils.isEmpty(updateMM) || TextUtils.isEmpty(updateYY) || TextUtils.isEmpty(updateCVV)) {
                Toast.makeText(context,"Indtast venligst et gyldigt input",Toast.LENGTH_LONG).show()
            } else {

                // Update kortnummer
                val kortNummerUpdate = db.collection("payment_info").document(uidValue.toString())
                kortNummerUpdate
                    .update("kortNummer", updateKortNummer)
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "Cardnumber successfully updated!") }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error updating document", e) }

                val mmUpdate = db.collection("payment_info").document(uidValue.toString())
                mmUpdate
                    .update("mm", updateKortNummer)
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "Month successfully updated!") }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error updating document", e) }

                val yyUpdate = db.collection("payment_info").document(uidValue.toString())
                yyUpdate
                    .update("yy", updateKortNummer)
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "Year successfully updated!") }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error updating document", e) }

                val cvvUpdate = db.collection("payment_info").document(uidValue.toString())
                cvvUpdate
                    .update("cvv", updateKortNummer)
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "CVV successfully updated!") }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error updating document", e) }

                Navigation.findNavController(view).navigate(R.id.editBetalingToBetalingOplysning)
                Toast.makeText(context, "Kommet til Betalingsoplysninger", Toast.LENGTH_SHORT).show()
            }

            runBlocking {
                setData()

            }


        }

        val tilbageButton: Button = view.findViewById(R.id.tilbageButton)
        tilbageButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.betalingToIndstilling)

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

            val nytKortNummer = requireView().findViewById<TextView>(R.id.kortnummerEditview)
            val nytMM = requireView().findViewById<TextView>(R.id.MM)
            val nytYY = requireView().findViewById<TextView>(R.id.YY)
            val nytCVV = requireView().findViewById<TextView>(R.id.CVV)


            // get the current data from the database
            db.collection("payment_info").document(uid)
                .get()
                .addOnSuccessListener { doc ->


                    nytKortNummer.text = doc.getString("kortNummer").toString()
                    nytMM.text = doc.getString("mm").toString()
                    nytYY.text = doc.getString("yy").toString()
                    nytCVV.text = doc.getString("cvv").toString()

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
            val nytKortNummer = requireView().findViewById<TextView>(R.id.kortnummerEditview)
            val nytMM = requireView().findViewById<TextView>(R.id.MM)
            val nytYY = requireView().findViewById<TextView>(R.id.YY)
            val nytCVV = requireView().findViewById<TextView>(R.id.CVV)

            var newUser : kortNumberData = kortNumberData(nytKortNummer.text.toString(), nytMM.text.toString(), nytYY.text.toString(), nytCVV.text.toString())


            // save the edited
            db.collection("payment_info").document(uid).set(newUser)
                .addOnSuccessListener { Toast.makeText(context, "text works", Toast.LENGTH_SHORT).show()  }
                .addOnFailureListener { Toast.makeText(context, "text does not work", Toast.LENGTH_SHORT).show()  }
        }

    }





}