package com.dtu.nemsport.view.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import com.dtu.nemsport.R
import com.dtu.nemsport.models.FakeDB
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Text

class betalingsOplysninger : Fragment() {

    lateinit var kortnummerText: TextView
    lateinit var MMText: TextView
    lateinit var YYText: TextView
    lateinit var CVVText: TextView
    private lateinit var db: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_betalings_oplysninger, container, false)

        kortnummerText = view.findViewById(R.id.kortnummerText)
        MMText = view.findViewById(R.id.MMText)
        YYText = view.findViewById(R.id.YYText)
        CVVText = view.findViewById(R.id.CVVText)

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

            kortnummerText = requireView().findViewById(R.id.kortnummerText)
            MMText = requireView().findViewById(R.id.MMText)
            YYText = requireView().findViewById(R.id.YYText)
            CVVText = requireView().findViewById(R.id.CVVText)

            db.collection("payment_info").document(uid)
                .get()
                .addOnSuccessListener { doc ->
                    kortnummerText.text = doc.getString("kortNummer")
                    MMText.text = doc.getString("mm")
                    YYText.text = doc.getString("yy")
                    CVVText.text = doc.getString("cvv")
                }
                .addOnFailureListener {
                    // TODO: maybe we need to do it some other way than this xDDDDD
                    kortnummerText.text = "error"
                    MMText.text = "error"
                    YYText.text = "error"
                    CVVText.text = "error"
                }
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 00 - get user id
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        var uid = sharedPref.getString("nemsport_uid", "")

        if (uid != null) {
            Log.w("uid - profil - onViewCreated:", uid)
        }

        runBlocking {
            getAndSetData()
        }



        val tilbageButton: Button = view.findViewById(R.id.tilbageButton)
        tilbageButton.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.betalingOplysningerToIndstillinger)
        }

    }


}