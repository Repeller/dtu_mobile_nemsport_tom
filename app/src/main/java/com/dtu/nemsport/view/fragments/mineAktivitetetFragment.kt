package com.dtu.nemsport.view.fragments

import android.app.ActionBar
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dtu.nemsport.R
import com.dtu.nemsport.adapter.AktivitetAdapter
import com.dtu.nemsport.models.AktivitetData
import com.dtu.nemsport.models.FakeDB
import com.google.firebase.Timestamp

class mineAktivitetetFragment : Fragment() {

    private lateinit var visAlleAktiviteterKnap: Button
    private lateinit var tilføjNyAktivitetKnap2: Button
    private lateinit var recycler: RecyclerView
    private lateinit var aktivitetList: ArrayList<AktivitetData>
    private lateinit var aktivitetAdapter: AktivitetAdapter

    var fakeDB = FakeDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine_aktivitetet, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val sharedPref = activity?.getSharedPreferences("shared", Context.MODE_PRIVATE)
        val defaultValue = false
        val medlemStatus = sharedPref!!.getBoolean("medlemStatus", defaultValue)

        aktivitetList = ArrayList()

        tilføjNyAktivitetKnap2 = view.findViewById(R.id.tilføjNyAktivitetKnap2)

        if(!medlemStatus) {
            tilføjNyAktivitetKnap2.visibility = View.GONE
        }

        recycler = view.findViewById(R.id.recyclerView2)


        visAlleAktiviteterKnap = view.findViewById(R.id.visalleaktiviteter)

        aktivitetAdapter = AktivitetAdapter(fakeDB.myListData)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = aktivitetAdapter

        tilføjNyAktivitetKnap2.setOnClickListener {
            addInfo()
        }

        visAlleAktiviteterKnap.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.aktiviteterFragment)
        }

        //fakeDB.listData.add(AktivitetData("overskrift1", "spillere1", "dato1", "note1"))

        if(fakeDB.myListData.size > 1) {
            aktivitetList.add(AktivitetData(fakeDB.myListData.get(1).overskrift, fakeDB.myListData.get(1).maxAntalSpillere, fakeDB.myListData.get(1).dato, fakeDB.myListData.get(1).note))
            Toast.makeText(context,fakeDB.myListData.get(1).overskrift , Toast.LENGTH_SHORT).show()
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
        addDialog.setPositiveButton("Ok") {
                dialog, i->
            val overskrifter = overskrift.text.toString()
            val maxAntalSpillere = maxAntalSpillere.text.toString()
            val datoer = dato.text.toString()
            val noter = note.text.toString()

            //aktivitetList.add(AktivitetData("$overskrifter", "$maxAntalSpillere", "$datoer", "$noter"))

            val activity = fakeDB.db.collection("activity")

            val data = hashMapOf(
                "date" to Timestamp.now(),
                "made_by" to FakeDB.userUID,
                "max_players" to maxAntalSpillere.toLong(),
                "note" to noter,
                "title" to overskrifter
            )

            activity.add(data)
                .addOnSuccessListener { documentReference ->
                    Log.d(ContentValues.TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }

            fakeDB.myListData.add(AktivitetData("$overskrifter","$maxAntalSpillere","$datoer","$noter"))
            val lastObjectIndex = fakeDB.myListData.size-1
            aktivitetList.add(AktivitetData(fakeDB.myListData.get(lastObjectIndex).overskrift, fakeDB.myListData.get(lastObjectIndex).maxAntalSpillere, fakeDB.myListData.get(lastObjectIndex).dato, fakeDB.myListData.get(lastObjectIndex).note))
            Toast.makeText(context, fakeDB.myListData.get(0).overskrift, Toast.LENGTH_SHORT).show()

            aktivitetAdapter.notifyDataSetChanged()
            dialog.dismiss()

            FakeDB.getAllData()

        }
        addDialog.setNegativeButton("Cancel") {
                dialog, i->
            dialog.dismiss()
            Toast.makeText(context, "Fortrudt", Toast.LENGTH_SHORT).show()

        }

        addDialog.create()
        addDialog.show()


    }
}