package com.example.murray

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class PatientHomeCaregiverActivity : AppCompatActivity(), OnClickListener {

    private lateinit var editTextLocation: EditText
    private lateinit var textViewChooseLocation: TextView
    private lateinit var editTextDetails: EditText
    private lateinit var buttonSaveHome: Button
    private var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_home_caregiver)

        initializeViews()
        setListeners()
    }

    private fun initializeViews() {
        editTextLocation = findViewById(R.id.editTextLocation)
        textViewChooseLocation = findViewById(R.id.textViewChooseLocation)
        editTextDetails = findViewById(R.id.editTextDetails)
        buttonSaveHome = findViewById(R.id.buttonSaveHome)

    }

    private fun setListeners() {
        buttonSaveHome.setOnClickListener(this)
        textViewChooseLocation.setOnClickListener(this)
    }

    override fun onClick(view: View?){
        if(view?.id == R.id.buttonSaveHome){
            if(editTextLocation.text.toString() == ""){
                val builder = AlertDialog.Builder(context)
                builder.setTitle(R.string.error_title_patient_home)
                builder.setMessage(R.string.location_not_entered)
                builder.setIcon(android.R.drawable.ic_dialog_alert)
                builder.setPositiveButton("Ok"){dialogInterface, which ->
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
            else{
                val builder = AlertDialog.Builder(context)
                builder.setTitle(R.string.success)
                builder.setMessage(R.string.success_message_home_saved)
                builder.setIcon(android.R.drawable.ic_dialog_info)
                builder.setPositiveButton("Ok"){dialogInterface, which ->
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }
        if(view?.id == R.id.textViewChooseLocation){
            val gmmIntentUri: Uri = Uri.parse("google.streetview:cbll=48.8583736,2.2922873")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }
}