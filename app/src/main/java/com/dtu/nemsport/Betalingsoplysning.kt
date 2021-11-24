package com.dtu.nemsport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class Betalingsoplysning : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_betalingsoplysning, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gemButton: Button = view.findViewById(R.id.gemButton)

        gemButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_betalingsoplysning_to_indstillingerFragment)
        }

        val tilbageButton: Button = view.findViewById(R.id.tilbageButton)

        tilbageButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_betalingsoplysning_to_indstillingerFragment)
        }
    }
}

