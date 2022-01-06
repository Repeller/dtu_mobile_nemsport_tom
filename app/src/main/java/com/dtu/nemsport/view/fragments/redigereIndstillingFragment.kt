package com.dtu.nemsport.view.fragments

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
import com.dtu.nemsport.models.UserProfileData


class RedigereIndstilling : Fragment() {

    lateinit var nyNavn: EditText
    lateinit var nyEmail: EditText
    lateinit var nyAdresse: EditText
    lateinit var nyNummer: EditText
    lateinit var sendBtn: Button
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

        sendBtn = view.findViewById(R.id.nyGemKnap)

        // Update each variable on button clicked and navigate to profilFragment
        sendBtn.setOnClickListener {
            val navn = nyNavn.text.toString()
            val email = nyEmail.text.toString()
            val adresse = nyAdresse.text.toString()
            val nummer = nyNummer.text.toString()
            val bigNavn = nyNavn.text.toString()

            fakeDB.userData[0].navn = navn
            fakeDB.userData[0].navn = bigNavn
            fakeDB.userData[0].email = email
            fakeDB.userData[0].adresse = adresse
            fakeDB.userData[0].nummer = nummer

            val action = RedigereIndstillingDirections.redigereProfilToProfil("")
            Navigation.findNavController(view).navigate(action)
            Toast.makeText(context, "Kommet til profil", Toast.LENGTH_SHORT).show()
        }

    }


}