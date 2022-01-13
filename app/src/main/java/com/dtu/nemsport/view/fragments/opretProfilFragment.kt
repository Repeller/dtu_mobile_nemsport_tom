package com.dtu.nemsport.view.fragments

import android.content.Context
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

        medlemState(view)


        opretButton.setOnClickListener {
            val sharedPref = activity?.getSharedPreferences("shared", Context.MODE_PRIVATE)
            with (sharedPref!!.edit()) {
                putBoolean("medlemStatus", medlemState(view))
                apply()
            }

            requireActivity().run {
                startActivity(Intent(this, MainPage::class.java))
                finish()
            }
        }

    }

    fun medlemState(view: View): Boolean {
        switch1 = view.findViewById(R.id.switch1)

        medlem = switch1.isChecked

        return medlem
    }


}
