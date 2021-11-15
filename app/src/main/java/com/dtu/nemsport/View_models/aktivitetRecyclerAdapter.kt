package com.dtu.nemsport.View_models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dtu.nemsport.R
import com.dtu.nemsport.models.Event

// learn a lot from this video
// https://youtu.be/HtwDXRWjMcU - RECYCLERVIEW - Android Fundamentals - by Philipp Lackner

//class aktivitetRecyclerAdapter (var events: List<Event>) : RecyclerView.Adapter<aktivitetRecyclerAdapter.aktivitetViewHolder>() {
//
//    inner class aktivitetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): aktivitetViewHolder {
//
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.aktivitet_item, parent, false)
//        return aktivitetViewHolder(view)
//    }
//
//    // here we bind our data to  our items
//    override fun onBindViewHolder(holder: aktivitetViewHolder, position: Int) {
//        holder.itemView.apply {  tvOverskift.text = events[position].title, }
//    }
//
//    // returns the count of items in our recycler view
//    override fun getItemCount(): Int {
//        return events.size
//    }
//}