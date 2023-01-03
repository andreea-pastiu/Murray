package com.example.murray

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.util.Log.INFO
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.logging.Level.INFO

class AccountActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var backButtonImageView: ImageView
    private lateinit var accountPhotoImageView: ImageView
    private lateinit var nameTagTextView: TextView
    private lateinit var containerDetailsImageView: ImageView
    private lateinit var containerPasswordImageView: ImageView
    private lateinit var containerProblemImageView: ImageView
    private lateinit var logOutButton: Button
    private lateinit var userName: String
    private lateinit var userSurname: String
    private lateinit var userPassword: String

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Log.d("Afisare", "OK")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_account)

        initializeViews()
        populateViews()
        setListeners()
    }

    private fun initializeViews() {
        backButtonImageView = findViewById(R.id.imageViewBackButton)
        accountPhotoImageView = findViewById(R.id.imageViewAccountPhoto)
        nameTagTextView = findViewById(R.id.textViewNameTag)
        containerDetailsImageView = findViewById(R.id.imageViewContainerButton1)
        containerPasswordImageView = findViewById(R.id.imageViewContainerButton2)
        containerProblemImageView = findViewById(R.id.imageViewContainerButton3)
        logOutButton = findViewById(R.id.buttonLogOut)
    }

    private fun populateViews() {
        userName = intent.getStringExtra("user_name").toString()
        userSurname = intent.getStringExtra("user_surname").toString()
        nameTagTextView.text = userName + " " + userSurname
        userPassword = intent.getStringExtra("user_password").toString()
        //accountPhotoImageView.background = getDrawable(resources.obtainTypedArray(R.array.drawable_array).getResourceId(resources.getStringArray(R.array.title_array).indexOf(intent.getStringExtra("title").toString()),0 ))
    }

    private fun setListeners() {
        backButtonImageView.setOnClickListener(this)
        logOutButton.setOnClickListener(this)
        accountPhotoImageView.setOnClickListener(this)
        containerDetailsImageView.setOnClickListener(this)
        containerPasswordImageView.setOnClickListener(this)
        containerProblemImageView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            backButtonImageView -> onBackPressed()
            logOutButton -> TODO("Start main activity")
            accountPhotoImageView -> buildAlertDialog()
            containerDetailsImageView -> openDetails()
            containerPasswordImageView -> openPassword()
            containerProblemImageView -> openProblem()
        }
    }

    private fun buildAlertDialog() {
        val dialogBinding = layoutInflater.inflate(R.layout.dialog_custom_1, null)

        val myDialog = Dialog(this)
        myDialog.setContentView(dialogBinding)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()

        val cancelButton = dialogBinding.findViewById<Button>(R.id.buttonCancelDialog1)
        cancelButton.setOnClickListener{
            myDialog.dismiss()
        }
        //TODO("Implement open gallery and camera! Maybe try add that shadow and blur")
    }

    private fun openDetails() {
        val intent = Intent ( this, AccountDetailsActivity::class.java )
        intent.putExtra("name", userName)
        intent.putExtra("surname", userSurname)
        resultLauncher.launch(intent)
    }

    private fun openPassword() {
        val intent = Intent ( this, AccountPasswordActivity::class.java )
        intent.putExtra("password", userPassword)
        resultLauncher.launch(intent)
    }

    private fun openProblem() {
        startActivity(Intent(this, AccountProblemActivity::class.java))
    }
}