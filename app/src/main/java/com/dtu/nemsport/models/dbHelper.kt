package com.dtu.nemsport.models

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.preference.PreferenceManager
import com.dtu.nemsport.view.MainPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.runBlocking

class dbHelper(database: FirebaseFirestore, authPar: FirebaseAuth) {

    var db = database
    var auth = authPar

    suspend fun login(currentContent : FragmentActivity, email: String, password: String) : Boolean
    {
        var output = false

        runBlocking {

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(currentContent) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        Log.d(ContentValues.TAG, "signInWithEmail:success - " + user.toString())
                        // updateUI(user)
                        if (user != null) {
                            runBlocking {
                                saveToSharedPref(currentContent, "nemsport_uid", user.uid)
                            }

                            // TODO: find a way to change the page
                            output = true
                            // startActivity(Intent(this, MainPage::class.java))
                            //startActivity(Intent(this, MainPage::class.java))
                            // finish()
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            currentContent, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                        output = false

                        // updateUI(null)
                        // out_feedback.text = "you have the wrong email or password"
                    }
                }
        }

        return output
    }

    suspend fun saveAccountInDatabase(currentContent : FragmentActivity, userId: String, name: String,
                                      email: String, phone:String, address: String, member: Boolean) : Boolean {

        // database = FirebaseFirestore.getInstance()

        val user: MutableMap<String, Any> = hashMapOf()
        user["name"] = name
        user["mail"] = email
        user["phone"] = phone
        user["address"] = address
        user["member"] = member

        var output = false

        runBlocking {
            db.collection("users").document(userId).set(user)
                .addOnSuccessListener {
                    output = true

                    Toast.makeText(
                        currentContent,
                        "the user have been added",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener {
                    output = false

                    Toast.makeText(
                        currentContent,
                        "the user could not be added",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

        return output
    }

    suspend fun saveToSharedPref(currentContent : FragmentActivity, key : String, value: String) : Boolean {
        var output : Boolean

        runBlocking {
            var sharedPref = PreferenceManager.getDefaultSharedPreferences(currentContent)

            var editor : SharedPreferences.Editor = sharedPref.edit()
            output = editor.putString(key, value).commit()
        }

        return output
    }

    suspend fun newAccount(currentContent : FragmentActivity, name: String, email: String, password: String, phone: String, address: String) : Boolean {

        var output = false

        runBlocking {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(currentContent) { task ->
                    // add the user to the list of accounts
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(ContentValues.TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        Log.i("auth user info:" , user.toString())

                        // 03 - add the user-info to the database
                        val tempUserId = auth.currentUser?.uid

                        if (tempUserId != null) {

                            runBlocking{
                                saveAccountInDatabase(currentContent,
                                    tempUserId,
                                    name,
                                    email,
                                    phone,
                                    address,
                                    true)
                            }

                            // 04 - save the UID and 'medlemStatus' in sharedPref
                            runBlocking {
                                saveToSharedPref(currentContent, "nemsport_uid", tempUserId)
                                saveToSharedPref(currentContent, "medlemStatus", "true")
                            }

                            output = true

                            // TODO: find a way to change the page
                            // startActivity(Intent(this, MainPage::class.java))
                            // finish()

                            //updateUI(user)
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        output = false

                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(currentContent, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        //updateUI(null)

                        // out_feedback.text = "This email is already in the system, it needs to be unique"
                    }
                }
        }
        return output
    }
}