package com.example.murray

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactPageActivity : AppCompatActivity(), OnClickListener {

    private lateinit var textViewName: TextView
    private lateinit var textViewPhoneNumber2: TextView
    private lateinit var textViewRelationship2: TextView
    private lateinit var textViewAddress2: TextView
    private lateinit var imageViewContact: ImageView
    private lateinit var buttonCall: Button
    private lateinit var buttonVisit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_page)

        initializeViews()
        populateViews()
        setListeners()
    }

    private fun initializeViews() {
        textViewName = findViewById(R.id.textViewName)
        textViewPhoneNumber2 = findViewById(R.id.textViewPhoneNumber2)
        textViewRelationship2 = findViewById(R.id.textViewRelationship2)
        textViewAddress2 = findViewById(R.id.textViewAddress2)
        imageViewContact = findViewById(R.id.imageViewContact)
        buttonCall = findViewById(R.id.buttonCall)
        buttonVisit = findViewById(R.id.buttonVisit)

    }

    private fun setListeners() {
        buttonCall.setOnClickListener(this)
        buttonVisit.setOnClickListener(this)
    }

    private fun populateViews() {
        textViewName.text = intent.getStringExtra("name")
        textViewPhoneNumber2.text = intent.getStringExtra("phone")
        textViewRelationship2.text = intent.getStringExtra("relationship")
        textViewAddress2.text = intent.getStringExtra("address")
        imageViewContact.setImageDrawable(getDrawable(resources.obtainTypedArray(R.array.contact_images).getResourceId(intent.getIntExtra("index", 0), 0)) )
    }


    override fun onClick(view: View?) {
        if(view?.id == R.id.buttonCall){
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + textViewPhoneNumber2.text)
            startActivity(intent)
        }
        if(view?.id == R.id.buttonVisit){
            val gmmIntentUri: Uri = Uri.parse("google.streetview:cbll=48.8583736,2.2922873")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }
}