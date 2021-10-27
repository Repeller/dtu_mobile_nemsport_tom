package com.dtu.nemsport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RedigereIndstilling.newInstance] factory method to
 * create an instance of this fragment.
 */
class RedigereIndstilling : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var nyGemKnap: Button

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
        return inflater.inflate(R.layout.fragment_redigere_indstilling, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Laver variable på vores EditText som vi henter på deres id
        val nyNavn: EditText = view.findViewById(R.id.edtxNavn)
        val nyEmail: EditText = view.findViewById(R.id.edtxEmail)
        val nyAdresse: EditText = view.findViewById(R.id.edtxAdresse)
        val nyNummer: EditText = view.findViewById(R.id.edtxNummer)

        nyGemKnap = view.findViewById(R.id.nyGemKnap)
        nyGemKnap.setOnClickListener {
            val navnBesked: String = nyNavn.getText().toString()
            val emailBesked: String = nyEmail.getText().toString()
            val adresseBesked: String = nyAdresse.getText().toString()
            val nummerBesked: String = nyNummer.getText().toString()


            fragmentManager?.commit {
                setReorderingAllowed(true)

                replace<profilFragment>(R.id.fragmentContainerView)

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
         * @return A new instance of fragment RedigereIndstilling.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RedigereIndstilling().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}