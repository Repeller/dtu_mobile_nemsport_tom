package com.dtu.nemsport.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dtu.nemsport.R
import com.dtu.nemsport.models.AktivitetData
import com.dtu.nemsport.adapter.AktivitetAdapter
import com.dtu.nemsport.models.FakeDB

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AktivitetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AktivitetFragment : Fragment() {

    var fakeDB = FakeDB

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var tilføjNyAktivitetKnap: Button
    private lateinit var recycler: RecyclerView
    private lateinit var aktivitetList: ArrayList<AktivitetData>
    private lateinit var aktivitetAdapter: AktivitetAdapter

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
        return inflater.inflate(R.layout.fragment_aktiviteter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aktivitetList = ArrayList()

        tilføjNyAktivitetKnap = view.findViewById(R.id.tilføjNyAktivitetKnap)
        recycler = view.findViewById(R.id.recyclerView)

        aktivitetAdapter = AktivitetAdapter(this,aktivitetList)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = aktivitetAdapter

        tilføjNyAktivitetKnap.setOnClickListener {
            addInfo()
        }

        //fakeDB.listData.add(AktivitetData("overskrift1", "spillere1", "dato1", "note1"))

        if(fakeDB.listData.size > 1) {
            aktivitetList.add(AktivitetData(fakeDB.listData.get(1).overskrift, fakeDB.listData.get(1).maxAntalSpillere, fakeDB.listData.get(1).dato, fakeDB.listData.get(1).note))
            Toast.makeText(context,fakeDB.listData.get(1).overskrift , Toast.LENGTH_SHORT).show()
        }

    }

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
            fakeDB.listData.add(AktivitetData("$overskrifter","$maxAntalSpillere","$datoer","$noter"))
            aktivitetList.add(AktivitetData(fakeDB.listData.get(1).overskrift, fakeDB.listData.get(1).maxAntalSpillere, fakeDB.listData.get(1).dato, fakeDB.listData.get(1).note))
            Toast.makeText(context, fakeDB.listData.get(0).overskrift, Toast.LENGTH_SHORT).show()

            aktivitetAdapter.notifyDataSetChanged()
            dialog.dismiss()

        }
        addDialog.setNegativeButton("Cancel") {
            dialog, i->
            dialog.dismiss()
            Toast.makeText(context, "Fortrudt", Toast.LENGTH_SHORT).show()

        }

        addDialog.create()
        addDialog.show()


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AktivitetFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AktivitetFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}