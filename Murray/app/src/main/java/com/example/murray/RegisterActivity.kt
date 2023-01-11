package com.example.murray

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class RegisterActivity : AppCompatActivity(), OnClickListener {

    private lateinit var editTextFirstName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextRepeatPassword: EditText
    private lateinit var buttonRegister: Button
    private var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initializeViews()
        setListeners()
    }

    private fun initializeViews() {
        editTextFirstName = findViewById(R.id.editTextFirstName)
        editTextLastName = findViewById(R.id.editTextLastName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword)
        buttonRegister = findViewById(R.id.buttonRegister)
    }

    private fun setListeners() {
        buttonRegister.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        var error = ""
        if(editTextFirstName.text.toString() == ""){
            error += getString(R.string.firstName_required) + '\n'
        }
        if(editTextLastName.text.toString() == ""){
            error += getString(R.string.lastName_required) + '\n'
        }
        if(editTextEmail.text.toString() == ""){
            error += getString(R.string.email_required) + '\n'
        }
        if(editTextPassword.text.toString() == ""){
            error += getString(R.string.password_required) + '\n'
        }
        if(editTextPassword.text.length < 6){
            error += getString(R.string.password_length_error) + '\n'
        }
        if(editTextPassword.text.toString() == editTextRepeatPassword.text.toString()){
            error += getString(R.string.passwords_do_not_match) + '\n'
        }
        if(error != "") {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.error_title_register)
            builder.setMessage(error)
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Ok"){dialogInterface, which ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
        else {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.success)
            builder.setMessage(R.string.success_message_register)
            builder.setIcon(android.R.drawable.ic_dialog_info)
            builder.setPositiveButton("Ok"){dialogInterface, which ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }
}