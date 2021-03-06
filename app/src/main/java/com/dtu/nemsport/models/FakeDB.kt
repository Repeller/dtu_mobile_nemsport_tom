package com.dtu.nemsport.models

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

import androidx.annotation.RequiresApi

import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.R)
object FakeDB {

    val user = Firebase.auth.currentUser
    val userUID = user?.uid.toString()

    var listData = ArrayList<AktivitetData>()
    var kortNummberData = ArrayList<kortNumberData>()
    var userData = ArrayList<UserProfileData>()

    var myListData = ArrayList<AktivitetData>()


    var overskrift: String? = null
    var maxAntalSpillere: Long? = null
    var tilmeldteSpillere: Long? = null
    var dato: Timestamp? = null
    var note: String? = null

    var kortNummer: Long? = null
    var MM: Long? = null
    var YY: Long? = null
    var CVV: Long? = null


    @SuppressLint("StaticFieldLeak")
    val db = FirebaseFirestore.getInstance()

    @TargetApi(Build.VERSION_CODES.R)
    fun getUserData() {
        this.myListData = ArrayList<AktivitetData>()
        if (user != null) {
            db.collection("activity")
                .whereEqualTo("made_by", userUID)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        myListData.add(
                            AktivitetData(
                                document.id,
                                document.getString("title"),
                                document.getString("made_by"),
                                document.getLong("max_players"),
                                document.getLong("joined_amount"),
                                document.getTimestamp("date"),
                                document.getString("note")
                            )
                        )
                        Log.d("test2", "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("Fejl", "Error getting documents: ", exception)
                }
        }
    }

    fun checkUsersOnData() {
        // 00 - take in all a activity and check in "activity_user"
        var copyOfList = listData

        for ((i, item) in listData.withIndex())
        {
            db.collection("activity")
                .whereEqualTo("ActivityID", item.id)
                .get()
                .addOnSuccessListener { result ->
                    for (doc in result)
                    {
                        val temp = copyOfList[i].joined_amount!!.toInt() + 1
                        copyOfList[i].joined_amount = temp.toLong()
                    }

                    // 01 - save over the old list
                    listData = copyOfList
                }
        }
    }

    fun getAllData() {
        this.listData = ArrayList<AktivitetData>()
        db.collection("activity")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    listData.add(
                        AktivitetData(
                            document.id,
                            document.getString("title"),
                            document.getString("made_by"),
                            document.getLong("max_players"),
                            document.getLong("joined_amount"),
                            document.getTimestamp("date"),
                            document.getString("note")
                        )
                    )
                    Log.d("test2", "${document.id} => ${document.data}")
                }

            }
            .addOnFailureListener { exception ->
                Log.d("Fejl", "Error getting documents: ", exception)
            }
    }

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

        getAllData()


        getUserData()


        userData.add(UserProfileData("navn1", "email1", "adresse1", "nummer1"))
    }

}


