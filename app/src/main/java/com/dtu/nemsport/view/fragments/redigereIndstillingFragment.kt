package com.dtu.nemsport.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.dtu.nemsport.R
import com.dtu.nemsport.models.FakeDB
import com.dtu.nemsport.models.UserProfileData

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

    lateinit var nyNavn: EditText
    lateinit var nyEmail: EditText
    lateinit var nyAdresse: EditText
    lateinit var nyNummer: EditText
    lateinit var sendBtn: Button
    var fakeDB = FakeDB

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
        val view = inflater.inflate(R.layout.fragment_redigere_indstilling, container, false)


        nyNavn = view.findViewById(R.id.edtxNavn)
        nyEmail = view.findViewById(R.id.edtxEmail)
        nyAdresse = view.findViewById(R.id.edtxAdresse)
        nyNummer = view.findViewById(R.id.edtxNummer)

        nyNavn.setText(FakeDB.userData[0].navn)
        nyEmail.setText(FakeDB.userData[0].email)
        nyAdresse.setText(FakeDB.userData[0].adresse)
        nyNummer.setText(FakeDB.userData[0].nummer)


        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        sendBtn = view.findViewById(R.id.nyGemKnap)

        sendBtn.setOnClickListener {
            val navn = nyNavn.text.toString()
            val email = nyEmail.text.toString()
            val adresse = nyAdresse.text.toString()
            val nummer = nyNummer.text.toString()


            fakeDB.userData[0].navn = navn
            fakeDB.userData[0].email = email
            fakeDB.userData[0].adresse = adresse
            fakeDB.userData[0].nummer = nummer

            val action = RedigereIndstillingDirections.redigereProfilToProfil("")
            Navigation.findNavController(view).navigate(action)
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