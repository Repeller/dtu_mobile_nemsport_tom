package com.dtu.nemsport.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.*
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import com.dtu.nemsport.R
import com.dtu.nemsport.models.FakeDB
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.runBlocking


class profilFragment : Fragment() {

    //private val args: profilFragmentArgs by navArgs()


    lateinit var skiftIndstillinger: Button
    lateinit var navn: TextView
    lateinit var bigNavnProfil: TextView
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profil, container, false)

        navn = view.findViewById(R.id.navn)
        bigNavnProfil = view.findViewById(R.id.bigNavnProfil)
        val nyEmail = view.findViewById<TextView>(R.id.email)
        val nyAdresse = view.findViewById<TextView>(R.id.adresse)
        val nyNummer = view.findViewById<TextView>(R.id.nummer)

        // Set each variable as the text from the FakeDB
//        navn.text = FakeDB.userData[0].navn
//        bigNavnProfil.text = FakeDB.userData[0].navn
//        nyEmail.text = FakeDB.userData[0].email
//        nyAdresse.text = FakeDB.userData[0].adresse
//        nyNummer.text = FakeDB.userData[0].nummer

        return view

    }

    suspend fun getAndSetData(){
        db = FirebaseFirestore.getInstance()

        // 00 - get user id
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        var uid = sharedPref.getString("nemsport_uid", "")

        if (uid != null) {
            Log.w("uid - profil - onViewCreated:", uid)
        }
        // 00 - get the values from the DB
        if (uid != null) {

            navn = requireView().findViewById(R.id.navn)
            bigNavnProfil = requireView().findViewById(R.id.bigNavnProfil)
            val nyEmail = requireView().findViewById<TextView>(R.id.email)
            val nyAdresse = requireView().findViewById<TextView>(R.id.adresse)
            val nyNummer = requireView().findViewById<TextView>(R.id.nummer)

            db.collection("users").document(uid)
                .get()
                .addOnSuccessListener { doc ->
                    navn.text = doc.getString("name")
                    bigNavnProfil.text = doc.getString("name")
                    nyEmail.text = doc.getString("mail")
                    nyAdresse.text = doc.getString("address")
                    nyNummer.text = doc.getString("phone")
                }
                .addOnFailureListener {
                    // TODO: maybe we need to do it some other way than this xDDDDD
                    navn.text = "error"
                    bigNavnProfil.text = "error"
                    nyEmail.text = "error"
                    nyAdresse.text = "error"
                    nyNummer.text = "error"
                }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        skiftIndstillinger = view.findViewById(R.id.skiftIndstillinger)

        // 00 - get user id
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        var uid = sharedPref.getString("nemsport_uid", "")

        if (uid != null) {
            Log.w("uid - profil - onViewCreated:", uid)
        }

        runBlocking {
            getAndSetData()
        }

        // Navigate to EditProfile fragment
        skiftIndstillinger.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.profilToRedigereProfil)
            Toast.makeText(context, "Kommet til edit profile", Toast.LENGTH_SHORT).show()
        }

    }




}