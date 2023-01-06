package com.example.murray

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity(), OnClickListener {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var errorLoginTextView: TextView
    private lateinit var loginButton: Button
    private lateinit var forgotPasswordButton: TextView
    private lateinit var signupButton: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initializeViews()
        setListeners()
    }

    private fun initializeViews() {
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        errorLoginTextView = findViewById(R.id.textViewErrorLogin)
        loginButton = findViewById(R.id.buttonLogin)
        forgotPasswordButton = findViewById(R.id.buttonForgotPassword)
        signupButton = findViewById(R.id.buttonSignUp)
    }

    private fun setListeners() {
        loginButton.setOnClickListener(this)
        forgotPasswordButton.setOnClickListener(this)
        signupButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.buttonForgotPassword) {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
        else {
            if(view?.id == R.id.buttonSignUp) {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
            else {
                if(view?.id == R.id.buttonLogin) {
                    if(emailEditText.text.toString() == "caregiver@gmail.com" && passwordEditText.text.toString() == "caregiver"){
                        errorLoginTextView.visibility = GONE
                        //go to Home Page
                        //val intent = Intent(this, HomePageActivity::class.java)
                        //intent.putExtra("type", "caregiver")
                        //startActivity(intent)
                        val intent = Intent(this, ContactListCaregiverActivity::class.java)
                        startActivity(intent)
                    } else {
                        if (emailEditText.text.toString() == "patient@gmail.com" && passwordEditText.text.toString() == "patient") {
                            errorLoginTextView.visibility = GONE
                            //go to Home Page
                            //val intent = Intent(this, HomePageActivity::class.java)
                            //intent.putExtra("type", "patient")
                            //startActivity(intent)
                        } else {
                            errorLoginTextView.visibility = VISIBLE
                        }
                    }
                }
            }
        }
    }
}