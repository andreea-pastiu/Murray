package com.example.murray

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LocationPatientFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var backButtonImageView: ImageView
    private lateinit var locationNameTextView: TextView
    private lateinit var locationAddressTextView: TextView
    private lateinit var goToLocationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_location_patient_form)

        initializeViews()
        populateViews()
        setListeners()
    }

    private fun initializeViews() {
        backButtonImageView = findViewById(R.id.imageViewBackButton)
        locationNameTextView = findViewById(R.id.textViewLocationName)
        locationAddressTextView = findViewById(R.id.textViewLocationAddress)
        goToLocationButton = findViewById(R.id.buttonGoToLocation)
    }

    private fun populateViews() {
        locationNameTextView.text = intent.getStringExtra("location_name").toString()
        locationAddressTextView.text = intent.getStringExtra("location_address").toString()
    }

    private fun setListeners() {
        backButtonImageView.setOnClickListener(this)
        goToLocationButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            backButtonImageView -> onBackPressed()
            goToLocationButton -> onLocationButtonClicked()
        }
    }

    private fun onLocationButtonClicked() {
        val gmmIntentUri: Uri = Uri.parse("google.streetview:cbll=48.8583736,2.2922873")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}