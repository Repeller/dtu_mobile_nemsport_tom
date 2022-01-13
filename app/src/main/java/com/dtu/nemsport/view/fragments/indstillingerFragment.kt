package com.dtu.nemsport.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.dtu.nemsport.R
import com.dtu.nemsport.view.MainActivity
import com.dtu.nemsport.view.MainPage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [indstillingerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class indstillingerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_indstillinger, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ændreBetalingInformationKnap: Button = view.findViewById(R.id.ændreBetalingInformationKnap)

        ændreBetalingInformationKnap.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.indstillingToBetaling)
        }

        val betalingInformationKnap: Button = view.findViewById(R.id.betalingInformationKnap)

        betalingInformationKnap.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_indstillingerFragment_to_betalingsOplysninger)
        }

        val medlemStatusButton: Button = view.findViewById(R.id.medlemStatusButton)

        medlemStatusButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.indstillingerToMedlemStatus)
        }

        val logud: Button = view.findViewById(R.id.logud)

        logud.setOnClickListener{
            requireActivity().run {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment indstillingerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            indstillingerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}