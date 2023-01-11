package com.example.murray

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class ForgotPasswordActivity : AppCompatActivity(), OnClickListener {
    private lateinit var editTextEmail: EditText
    private lateinit var buttonForgotPassword: Button
    private var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        initializeViews()
        setListeners()
    }

    private fun initializeViews() {
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonForgotPassword = findViewById(R.id.buttonForgotPassword)
    }

    private fun setListeners() {
        buttonForgotPassword.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        var error = ""
        if(editTextEmail.text.toString() == ""){
            error += getString(R.string.email_required) + '\n'
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
            builder.setMessage(R.string.success_message_forgot_password)
            builder.setIcon(android.R.drawable.ic_dialog_info)
            builder.setPositiveButton("Ok"){dialogInterface, which ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }
}