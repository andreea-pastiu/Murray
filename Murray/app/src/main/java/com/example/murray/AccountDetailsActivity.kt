package com.example.murray

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AccountDetailsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var backButtonImageView: ImageView
    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_account_details)

        initializeViews()
        populateViews()
        setListeners()
    }

    private fun initializeViews() {
        backButtonImageView = findViewById(R.id.imageViewBackButton)
        nameEditText = findViewById(R.id.editTextName)
        surnameEditText = findViewById(R.id.editTextSurname)
        updateButton = findViewById(R.id.buttonUpdate)
    }

    private fun populateViews() {
        nameEditText.setText(intent.getStringExtra("name").toString())
        surnameEditText.setText(intent.getStringExtra("surname").toString())
    }

    private fun setListeners() {
        backButtonImageView.setOnClickListener(this)
        updateButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        //TODO("Validate the names")
        when (p0) {
            backButtonImageView -> onBackPressed()
            updateButton -> onBackPressed()
        }
    }
}