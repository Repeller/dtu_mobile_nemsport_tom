package com.dtu.nemsport.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import com.dtu.nemsport.R
import com.dtu.nemsport.view.MainPage


class opretProfilFragment : Fragment() {

    lateinit var switch1: Switch

    var medlem = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_opret_profil, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val opretButton: Button = view.findViewById(R.id.opretButton)

        switch1 = view.findViewById(R.id.switch1)

        switch1.setOnCheckedChangeListener { compoundButton, onSwitch ->
            if(onSwitch) {
                Toast.makeText(context, "On", Toast.LENGTH_SHORT).show()
                medlem = true
            } else {
                Toast.makeText(context, "Off", Toast.LENGTH_SHORT).show()
                medlem = false
            }
        }


        opretButton.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, MainPage::class.java))
                finish()
            }
        }

    }

}
