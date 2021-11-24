package com.dtu.nemsport.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dtu.nemsport.AktivitetFragment
import com.dtu.nemsport.R
import com.dtu.nemsport.models.AktivitetData

class AktivitetAdapter(val c: AktivitetFragment, val aktivitetList: ArrayList<AktivitetData>): RecyclerView.Adapter<AktivitetAdapter.AktivitetViewHolder>() {

    inner class AktivitetViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        val overskrift = v.findViewById<TextView>(R.id.overskrift)
        val maxAntalSpillere = v.findViewById<TextView>(R.id.maxAntalSpillere)
        val dato = v.findViewById<TextView>(R.id.datoTv)
        val note = v.findViewById<TextView>(R.id.noteTv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AktivitetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.aktivitet_item_list, parent, false)
        return AktivitetViewHolder(v)
    }

    override fun onBindViewHolder(holder: AktivitetViewHolder, position: Int) {
        val newList = aktivitetList[position]
        holder.overskrift.text = newList.overskrift
        holder.maxAntalSpillere.text = newList.maxAntalSpillere
        holder.dato.text = newList.dato
        holder.note.text = newList.note

    }

    override fun getItemCount(): Int {
        return aktivitetList.size
    }

}