package com.dtu.nemsport.view.fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import android.widget.EditText
import android.widget.TextView

import android.widget.Switch

import android.widget.Toast
import com.dtu.nemsport.R
import com.dtu.nemsport.view.MainPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.util.Patterns
import androidx.annotation.RequiresApi
import com.dtu.nemsport.models.FakeDB
import com.dtu.nemsport.models.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit


class opretProfilFragment : Fragment() {

    lateinit var switch1: Switch

    var medlem = false

    // views and text inputs from the front page
    private lateinit var input_name: EditText
    private lateinit var input_email: EditText
    private lateinit var input_password1: EditText
    private lateinit var input_password2: EditText
    private lateinit var input_phone: EditText
    private lateinit var input_address: EditText
    private lateinit var out_feedback: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_opret_profil, container, false)
    }



    private fun addUser(userId: String, name: String, email: String, phone:String, address: String)
    {
        // video guide - https://youtu.be/5UEdyUFi_uQ
        // how to set id - https://firebase.google.com/docs/firestore/manage-data/add-data#kotlin+ktx

        db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = hashMapOf()
        user["name"] = name
        user["mail"] = email
        user["phone"] = phone
        user["address"] = address
        user["member"] = switch1.isChecked.toString()

        db.collection("users").document(userId).set(user)
            .addOnSuccessListener { Toast.makeText(context, "the user have been added", Toast.LENGTH_SHORT).show() }
            .addOnFailureListener { Toast.makeText(context, "the user could not be added", Toast.LENGTH_SHORT).show() }

//        db.database.getReference
//        var user_info = User(email,  name, phone, address)
//
//        db.child("users").child(userId).setValue(user_info)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // 00 - set the views/inputs to our local vars
        input_name = view.findViewById(R.id.editTextPersonName2)
        input_email = view.findViewById(R.id.editTextEmailAddress)
        input_password1 = view.findViewById(R.id.editTextPassword)
        input_password2 = view.findViewById(R.id.editTextPassword2)
        input_phone = view.findViewById(R.id.editTextPhone)
        input_address = view.findViewById(R.id.editTextPostalAddress)
        val opretButton: Button = view.findViewById(R.id.opretButton)
        out_feedback = view.findViewById(R.id.textView_feedback)
        auth = Firebase.auth

        super.onViewCreated(view, savedInstanceState)

        var allFilledOut = false
        var validationDone = false
        var isPasswordsTheSame = false
        var emailValidaton = false

        // TODO: write validation for: phone number, address (for now it takes anything you write in it as correct inputs)
        // 01 - validation of input


        medlemState(view)



        opretButton.setOnClickListener {
            val sharedPref = activity?.getSharedPreferences("shared", Context.MODE_PRIVATE)
            with (sharedPref!!.edit()) {
                putBoolean("medlemStatus", medlemState(view))
                apply()
            }

            requireActivity().run {
                // checking if any of the inputs are empty
                if(input_name.text.toString().isBlank() ||
                    input_email.text.toString().isBlank() ||
                    input_password1.text.toString().isBlank() ||
                    input_password2.text.toString().isBlank() ||
                    input_phone.text.toString().isBlank() ||
                    input_address.text.toString().isBlank())
                {
                    allFilledOut = false
                    out_feedback.text = "Please make sure all fields are filled in correctly"
                }
                else // check password and email
                {
                    allFilledOut = true
                    // check password
                    if(input_password1.text.toString() == input_password2.text.toString())
                        isPasswordsTheSame = true
                    else {
                        isPasswordsTheSame = false
                        out_feedback.text = "You don't have the same password in both fields"
                    }

                    // check email
                    if(Patterns.EMAIL_ADDRESS.matcher(input_email.text.toString()).matches())
                        emailValidaton = true
                    else
                    {
                        emailValidaton = false
                        out_feedback.text = "You have not given a usable email address"
                    }

                    // set validationDone to true, if both checks works
                    validationDone = isPasswordsTheSame && emailValidaton && allFilledOut
                }


                if(validationDone)
                {
                    // 02 - if everything is okay, make the user

                    auth.createUserWithEmailAndPassword(input_email.text.toString(), input_password1.text.toString())
                        .addOnCompleteListener(this) { task ->
                            // add the user to the list of accounts
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success")
                                val user = auth.currentUser
                                Log.i("auth user info:" , user.toString())

                                // 03 - add the user-info to the database
                                //TODO: replace the id with the real one
                                val tempUserId = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()).toString()
                                addUser(tempUserId,
                                    input_name.text.toString(),
                                    input_email.text.toString(),
                                    input_phone.text.toString(),
                                    input_address.text.toString())

                                // 04 - save the UID in shared
                                // https://developer.android.com/training/data-storage/shared-preferences#WriteSharedPreference
                                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@addOnCompleteListener
                                with (sharedPref.edit()) {
                                    putString(getString(R.string.nemsport_uid), tempUserId)
                                    apply()
                                }


                                // nemsport_uid


                                startActivity(Intent(this, MainPage::class.java))
                                finish()

                                //updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                Toast.makeText(baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                                //updateUI(null)

                                out_feedback.text = "This email is already in the system, it needs to be unique"
                            }
                        }
                }
            }
        }

    }

    fun medlemState(view: View): Boolean {
        switch1 = view.findViewById(R.id.switch1)

        medlem = switch1.isChecked

        return medlem
    }


}
