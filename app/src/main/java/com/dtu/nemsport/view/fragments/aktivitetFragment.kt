package com.dtu.nemsport.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dtu.nemsport.R
import com.dtu.nemsport.models.AktivitetData
import com.dtu.nemsport.adapter.AktivitetAdapter
import com.dtu.nemsport.models.FakeDB
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import com.google.firebase.Timestamp
import kotlin.collections.ArrayList


class AktivitetFragment : Fragment() {

    var fakeDB = FakeDB
    var tilmeldte = 0

    private lateinit var tilføjNyAktivitetKnap: Button
    private lateinit var visMineAktiviteterKnap: Button
    private lateinit var recycler: RecyclerView
    private lateinit var aktivitetList: ArrayList<AktivitetData>
    private lateinit var aktivitetAdapter: AktivitetAdapter
    private lateinit var tilmeldteSpillere: TextView
    private lateinit var deltagKnap: Button
    private lateinit var frameldKnap: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aktiviteter, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getSharedPreferences("shared", Context.MODE_PRIVATE)
        val defaultValue = false
        val medlemStatus = sharedPref!!.getBoolean("medlemStatus", defaultValue)

        val sharedPref2 = activity?.getSharedPreferences("shared2", Context.MODE_PRIVATE)
        val defaultValue2 = false
        val medlemIndstillingStatus = sharedPref2!!.getBoolean("medlemIndstillingStatus", defaultValue2)


        aktivitetList = ArrayList()

        tilføjNyAktivitetKnap = view.findViewById(R.id.tilføjNyAktivitetKnap)

        if(!medlemStatus && !medlemIndstillingStatus) {
            tilføjNyAktivitetKnap.visibility = View.GONE
        }



        recycler = view.findViewById(R.id.recyclerView)


        visMineAktiviteterKnap = view.findViewById(R.id.vismineaktiviteter)

        aktivitetAdapter = AktivitetAdapter(fakeDB.listData)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = aktivitetAdapter

        tilføjNyAktivitetKnap.setOnClickListener {
            addInfo()
        }

        visMineAktiviteterKnap.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.fragment_mine_aktiviteter)
        }

        //fakeDB.listData.add(AktivitetData("overskrift1", "spillere1", "dato1", "note1"))

        if (fakeDB.listData.size > 1) {
            aktivitetList.add(
                AktivitetData(
                    fakeDB.listData.get(1).overskrift,
                    fakeDB.listData.get(1).maxAntalSpillere,
                    fakeDB.listData.get(1).dato,
                    fakeDB.listData.get(1).note
                )
            )
            Toast.makeText(context, fakeDB.listData.get(1).overskrift, Toast.LENGTH_SHORT).show()
        }




    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addInfo() {
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.add_list, null)

        val overskrift = v.findViewById<EditText>(R.id.overskriftInput)
        val maxAntalSpillere = v.findViewById<EditText>(R.id.maxAntalSpillereInput)
        val dato = v.findViewById<EditText>(R.id.datoInput)
        val note = v.findViewById<EditText>(R.id.noteInput)

        val addDialog = AlertDialog.Builder(requireActivity())

        addDialog.setTitle("Opret aktivitet")
        addDialog.setMessage("Indtast oplysninger")

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok") { dialog, i ->
            val overskrifter = overskrift.text.toString()
            val maxAntalSpillere = maxAntalSpillere.text.toString()
            val datoer = dato.text.toString()
            val noter = note.text.toString()

            //aktivitetList.add(AktivitetData("$overskrifter", "$maxAntalSpillere", "$datoer", "$noter"))

            val activity = fakeDB.db.collection("activity")

            val data = hashMapOf(
                "date" to Timestamp.now(),
                "made_by" to "",
                "max_players" to maxAntalSpillere.toLong(),
                "note" to noter,
                "title" to overskrifter
            )

            activity.add(data)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

            fakeDB.listData.add(
                AktivitetData(
                    "$overskrifter",
                    "$maxAntalSpillere",
                    "$datoer",
                    "$noter"
                )
            )
            val lastObjectIndex = fakeDB.listData.size - 1
            aktivitetList.add(
                AktivitetData(
                    fakeDB.listData.get(lastObjectIndex).overskrift,
                    fakeDB.listData.get(lastObjectIndex).maxAntalSpillere,
                    fakeDB.listData.get(lastObjectIndex).dato,
                    fakeDB.listData.get(lastObjectIndex).note
                )
            )
            Toast.makeText(context, fakeDB.listData.get(0).overskrift, Toast.LENGTH_SHORT).show()

            aktivitetAdapter.notifyDataSetChanged()
            dialog.dismiss()

        }
        addDialog.setNegativeButton("Cancel") { dialog, i ->
            dialog.dismiss()
            Toast.makeText(context, "Fortrudt", Toast.LENGTH_SHORT).show()

        }

        addDialog.create()
        addDialog.show()


    }

}