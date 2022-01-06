package com.dtu.nemsport.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.*
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.dtu.nemsport.R
import com.dtu.nemsport.models.FakeDB


class profilFragment : Fragment() {

    //private val args: profilFragmentArgs by navArgs()


    lateinit var skiftIndstillinger: Button
    lateinit var navn: TextView
    lateinit var bigNavnProfil: TextView


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
        navn.text = FakeDB.userData[0].navn
        bigNavnProfil.text = FakeDB.userData[0].navn
        nyEmail.text = FakeDB.userData[0].email
        nyAdresse.text = FakeDB.userData[0].adresse
        nyNummer.text = FakeDB.userData[0].nummer

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        skiftIndstillinger = view.findViewById(R.id.skiftIndstillinger)

        // Navigate to EditProfile fragment
        skiftIndstillinger.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.profilToRedigereProfil)
            Toast.makeText(context, "Kommet til edit profile", Toast.LENGTH_SHORT).show()
        }

    }




}