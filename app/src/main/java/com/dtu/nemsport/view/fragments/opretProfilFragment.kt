package com.dtu.nemsport.view.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.dtu.nemsport.R
import com.dtu.nemsport.view.MainPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.util.Patterns


class opretProfilFragment : Fragment() {


    // views and text inputs from the front page
    private lateinit var input_name: EditText
    private lateinit var input_email: EditText
    private lateinit var input_password1: EditText
    private lateinit var input_password2: EditText
    private lateinit var input_phone: EditText
    private lateinit var input_address: EditText
    private lateinit var out_feedback: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var btn_makeUser: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_opret_profil, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // 00 - set the views/inputs to our local vars
        input_name = view.findViewById(R.id.editTextPersonName2)
        input_email = view.findViewById(R.id.editTextEmailAddress)
        input_password1 = view.findViewById(R.id.editTextPassword)
        input_password2 = view.findViewById(R.id.editTextPassword2)
        input_phone = view.findViewById(R.id.editTextPhone)
        input_address = view.findViewById(R.id.editTextPostalAddress)

        out_feedback = view.findViewById(R.id.textView_feedback)
        btn_makeUser = view.findViewById(R.id.opretButton)

        auth = Firebase.auth

        super.onViewCreated(view, savedInstanceState)

        val opretButton: Button = view.findViewById(R.id.opretButton)

        var validationDone = false
        var isPasswordsTheSame = false
        var emailValidaton = false

        // TODO: write validation for: phone number, address (for now it takes anything you write in it as correct inputs)
        // 01 - validation of input




        opretButton.setOnClickListener {
            requireActivity().run {
                // checking if any of the inputs are empty
                if(input_name.text.toString().isNullOrBlank() ||
                    input_email.text.toString().isNullOrBlank() ||
                    input_password1.text.toString().isNullOrBlank() ||
                    input_password2.text.toString().isNullOrBlank() ||
                    input_phone.text.toString().isNullOrBlank() ||
                    input_address.text.toString().isNullOrBlank())
                {
                    validationDone = false
                    isPasswordsTheSame = false
                    out_feedback.text = "Please make sure all fields are filled in correctly"
                }
                else // check password and email
                {
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
                    validationDone = isPasswordsTheSame && emailValidaton
                }

                // just to make sure everything works, I check all 3 bool values
                if(validationDone && isPasswordsTheSame && emailValidaton)
                {
                    // 02 - if everything is okay, make the user

                    auth.createUserWithEmailAndPassword(input_email.text.toString(), input_password1.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success")
                                val user = auth.currentUser
                                Log.i("auth user info:" , user.toString())

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

}
