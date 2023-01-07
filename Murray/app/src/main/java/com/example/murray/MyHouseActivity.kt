package com.example.murray

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MyHouseActivity : AppCompatActivity(), OnClickListener {
    private lateinit var buttonTakeMeHome: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_house)

        initializeViews()
        setListeners()
    }

    private fun initializeViews() {
        buttonTakeMeHome = findViewById(R.id.buttonTakeMeHome)
    }

    private fun setListeners() {
        buttonTakeMeHome.setOnClickListener(this)
    }

    override fun onClick(view: View?){
        val gmmIntentUri: Uri = Uri.parse("google.streetview:cbll=48.8583736,2.2922873")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}