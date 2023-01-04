package com.example.murray

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.util.Log
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
    private lateinit var oldPasswordEyeImageView: ImageView
    private lateinit var newPasswordEyeImageView: ImageView
    private lateinit var repeatPasswordEyeImageView: ImageView
    private lateinit var userPassword: String
    private var showOldPassword: Boolean = true
    private var showNewPassword: Boolean = true
    private var showRepeatPassword: Boolean = true

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
        oldPasswordEyeImageView = findViewById(R.id.imageViewEye1)
        newPasswordEyeImageView = findViewById(R.id.imageViewEye2)
        repeatPasswordEyeImageView = findViewById(R.id.imageViewEye3)
    }

    private fun populateViews() {
        userPassword = intent.getStringExtra("password").toString()
    }

    private fun setListeners() {
        backButtonImageView.setOnClickListener(this)
        passwordButton.setOnClickListener(this)
        oldPasswordEyeImageView.setOnClickListener(this)
        newPasswordEyeImageView.setOnClickListener(this)
        repeatPasswordEyeImageView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            backButtonImageView -> onBackPressed()
            passwordButton -> changePassword()
            oldPasswordEyeImageView -> showUnshowOldPassword()
            newPasswordEyeImageView -> showUnshowNewPassword()
            repeatPasswordEyeImageView -> showUnshowRepeatPassword()
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
        if (oldPasswordEditText.text.toString().equals(userPassword)) {
            if (!oldPasswordEditText.text.toString().equals(newPasswordEditText.text.toString())) {
                if (newPasswordEditText.text.toString().equals(repeatPasswordEditText.text.toString())) {
                    onBackPressed()
                } else {
                    dialogTag.setText("Passwords do not match!")
                    myDialog.show()
                }
            } else {
                dialogTag.setText("Password needs to be new!")
                myDialog.show()
            }
        } else {
            dialogTag.setText("Old password is incorrect!")
            myDialog.show()
        }
    }

    private fun showUnshowOldPassword() {
        if (showOldPassword) {
            showOldPassword = false
            oldPasswordEditText.inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
        } else {
            showOldPassword = true
            oldPasswordEditText.inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        }
    }

    private fun showUnshowNewPassword() {
        if (showNewPassword) {
            showNewPassword = false
            newPasswordEditText.inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
        } else {
            showNewPassword = true
            newPasswordEditText.inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        }
    }

    private fun showUnshowRepeatPassword() {
        if (showRepeatPassword) {
            showRepeatPassword = false
            repeatPasswordEditText.inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
        } else {
            showRepeatPassword = true
            repeatPasswordEditText.inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        }
    }
}