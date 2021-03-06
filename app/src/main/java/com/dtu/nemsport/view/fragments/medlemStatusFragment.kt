package com.dtu.nemsport.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import androidx.navigation.Navigation
import com.dtu.nemsport.R



class medlemStatusFragment : Fragment() {

    lateinit var medlemSwitch: Switch

    var medlem = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medlemstatus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val gemButton: Button = view.findViewById(R.id.gemButton)

        // TODO: remove later, when the member system is working
        val temp = view.findViewById<Switch>(R.id.medlemIndstillingSwitch)
        temp.isChecked = true

        medlemState(view)

        gemButton.setOnClickListener {
            val sharedPref = activity?.getSharedPreferences("shared2", Context.MODE_PRIVATE)
            with(sharedPref!!.edit()) {
                putBoolean("medlemIndstillingStatus", medlemState(view))
                apply()
            }

            Navigation.findNavController(view).navigate(R.id.action_medlemStatus_to_indstillingerFragment)
        }

        val tilbageButton: Button = view.findViewById(R.id.tilbageButton)
        tilbageButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_medlemStatus_to_indstillingerFragment)
        }

    }

    fun medlemState(view: View): Boolean {
        medlemSwitch = view.findViewById(R.id.medlemIndstillingSwitch)

        medlem = medlemSwitch.isChecked

        return medlem
    }


}