package com.example.murray

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AccountPasswordActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var backButtonImageView: ImageView
    private lateinit var oldPasswordEditText: EditText
    private lateinit var newPasswordEditText: EditText
    private lateinit var repeatPasswordEditText: EditText
    private lateinit var passwordButton: Button
    private lateinit var userPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_account_password)

        initializeViews()
        populateViews()
        setListeners()
    }

    private fun initializeViews() {
        backButtonImageView = findViewById(R.id.imageViewBackButton)
        oldPasswordEditText = findViewById(R.id.editTextOldPassword)
        newPasswordEditText = findViewById(R.id.editTextNewPassword)
        repeatPasswordEditText = findViewById(R.id.editTextRepeatPassword)
        passwordButton = findViewById(R.id.buttonChangePassword)
    }

    private fun populateViews() {
        userPassword = intent.getStringExtra("password").toString()
    }

    private fun setListeners() {
        backButtonImageView.setOnClickListener(this)
        passwordButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            backButtonImageView -> onBackPressed()
            passwordButton -> changePassword()
        }
    }

    private fun changePassword() {
        val dialogBinding = layoutInflater.inflate(R.layout.dialog_custom_simple, null)

        val myDialog = Dialog(this)
        myDialog.setContentView(dialogBinding)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogTag = dialogBinding.findViewById<TextView>(R.id.textViewDialogTag)
        val cancelButton = dialogBinding.findViewById<Button>(R.id.buttonOkDialog)
        cancelButton.setOnClickListener{
            myDialog.dismiss()
        }
        if (!oldPasswordEditText.text.equals(newPasswordEditText.text)) {
            if (newPasswordEditText.text.equals(repeatPasswordEditText.text)) {
                onBackPressed()
            } else {
                dialogTag.setText("Passwords do not match!")
                myDialog.show()
            }
        } else {
            dialogTag.setText("Password needs to be new!")
            myDialog.show()
        }
    }
}