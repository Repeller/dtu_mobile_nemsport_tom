package com.dtu.nemsport.models

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

import androidx.annotation.RequiresApi

import com.google.firebase.Timestamp
import com.google.firebase.firestore.auth.User
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.R)
object FakeDB {
    var listData = ArrayList<AktivitetData>()
    var kortNummberData = ArrayList<kortNumberData>()
    var userData = ArrayList<UserProfileData>()

    var myListData = ArrayList<AktivitetData>()


    var overskrift: String? = null
    var maxAntalSpillere: Long? = null
    var dato: Timestamp? = null
    var note: String? = null

    var kortNummer: Long? = null
    var MM: Long? = null
    var YY: Long? = null
    var CVV: Long? = null


    @SuppressLint("StaticFieldLeak")
    val db = FirebaseFirestore.getInstance()

    init {
        db.collection("payment_info")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    kortNummer = document.getLong("card_number")
                    MM = document.getLong("exp_month")
                    YY = document.getLong("exp_year")
                    CVV = document.getLong("CVV")
                    kortNummberData.add(
                        kortNumberData(
                            kortNummer.toString(),
                            MM.toString(),
                            YY.toString(),
                            CVV.toString()
                        )
                    )
                    Log.d("test3", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Fejl", "Error getting documents: ", exception)
            }

        db.collection("activity")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    overskrift = document.getString("title")
                    maxAntalSpillere = document.getLong("max_players")
                    dato = document.getTimestamp("date")
                    note = document.getString("note")
                    listData.add(
                        AktivitetData(
                            overskrift,
                            maxAntalSpillere.toString(),
                            dato.toString(),
                            note
                        )
                    )
                    Log.d("test2", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Fejl", "Error getting documents: ", exception)
            }

        db.collection("activity")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.get("made_by")?.equals("2NvypJfm8jdCEeiI9ViN") == true) {
                        overskrift = document.getString("title")
                        maxAntalSpillere = document.getLong("max_players")
                        dato = document.getTimestamp("date")
                        note = document.getString("note")
                        myListData.add(
                            AktivitetData(
                                overskrift,
                                maxAntalSpillere.toString(),
                                dato.toString(),
                                note
                            )
                        )
                        Log.d("test2", "${document.id} => ${document.data}")
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Fejl", "Error getting documents: ", exception)
            }

        userData.add(UserProfileData("navn1","email1","adresse1","nummer1"))
    }

}


