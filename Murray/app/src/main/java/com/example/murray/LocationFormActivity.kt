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
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LocationFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var backButtonImageView: ImageView
    private lateinit var locationsSubtitleTextView: TextView
    private lateinit var enterLocationNameEditText: EditText
    private lateinit var chooseLocationTextView: TextView
    private lateinit var enterLocationAddressEditText: EditText
    private lateinit var formButton: Button
    private var updateLocationForm : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_location_form)

        initializeViews()
        populateViews()
        setListeners()
    }

    private fun initializeViews() {
        backButtonImageView = findViewById(R.id.imageViewBackButton)
        locationsSubtitleTextView = findViewById(R.id.textViewLocationsSubtitle)
        enterLocationNameEditText = findViewById(R.id.editTextEnterLocationName)
        chooseLocationTextView = findViewById(R.id.textViewChooseMapLocation)
        enterLocationAddressEditText = findViewById(R.id.editTextEnterAddress)
        formButton = findViewById(R.id.buttonAddLocation)
    }

    private fun populateViews() {
        if (intent.hasExtra("location_name")) {
            updateLocationForm = true
            locationsSubtitleTextView.text = "Edit a location"
            enterLocationNameEditText.setText(intent.getStringExtra("location_name").toString())
            enterLocationAddressEditText.setText(intent.getStringExtra("location_address").toString())
            formButton.text = "Update location"
        }
    }

    private fun setListeners() {
        backButtonImageView.setOnClickListener(this)
        chooseLocationTextView.setOnClickListener(this)
        formButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            backButtonImageView -> onBackPressed()
            chooseLocationTextView -> onChooseLocationClicked()
            formButton -> onFormButtonClicked()
        }
    }

    private fun onChooseLocationClicked() {
        val gmmIntentUri: Uri = Uri.parse("google.streetview:cbll=48.8583736,2.2922873")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    private fun onFormButtonClicked() {
        val dialogBinding = layoutInflater.inflate(R.layout.dialog_custom_success, null)

        val myDialog = Dialog(this)
        myDialog.setContentView(dialogBinding)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val succesText = dialogBinding.findViewById<TextView>(R.id.textViewDialogTag)
        if (updateLocationForm) {
            succesText.text = "Location updated successfully"
        } else {
            succesText.text = "Location added successfully"
        }
        myDialog.show()
        Handler(Looper.getMainLooper()).postDelayed({
            myDialog.dismiss()
            onBackPressed()
        }, 2000)
    }
}