package com.dtu.nemsport.models

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

import androidx.annotation.RequiresApi

import com.google.firebase.Timestamp


@RequiresApi(Build.VERSION_CODES.R)
object FakeDB {
    var listData = ArrayList<AktivitetData>()
    var kortNummberData = ArrayList<kortNumberData>()
    var userData = ArrayList<UserProfileData>()

    var overskrift: String? = null
    var maxAntalSpillere: Long? = null
    var dato: Timestamp? = null
    var note: String? = null



    @SuppressLint("StaticFieldLeak")
    var db = FirebaseFirestore.getInstance()



    init {
        db.collection("activity")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    overskrift = document.getString("title")
                    maxAntalSpillere = document.getLong("max_players")
                    dato = document.getTimestamp("date")
                    note = document.getString("note")
                    listData.add(AktivitetData(overskrift, maxAntalSpillere.toString(), dato.toString(), note))
                    Log.d("test2", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Fejl", "Error getting documents: ", exception)
            }

//        listData.add(AktivitetData(overskrift, "10", "dato1", "note1"))
        Log.d("Lol", "Hello world")
        kortNummberData.add(kortNumberData("", "","",""))
        userData.add(UserProfileData("navn1","email1","adresse1","nummer1"))
    }

}


