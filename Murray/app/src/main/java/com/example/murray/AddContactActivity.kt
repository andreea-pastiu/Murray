package com.example.murray

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class AddContactActivity : AppCompatActivity(), OnClickListener {

    private lateinit var editTextContactName: EditText
    private lateinit var editTextPhoneNumber: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var editTextRelationship: EditText
    private lateinit var textViewAddContact: TextView
    private lateinit var imageViewAddContact: ImageButton
    private lateinit var buttonAddContact: Button
    private var context: Context = this

    private val selectImageIntent = registerForActivityResult(ActivityResultContracts.GetContent())
    { uri ->
        imageViewAddContact.setImageURI(uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        initializeViews()
        populateViews()
        setListeners()
    }

    private fun initializeViews() {
        editTextContactName = findViewById(R.id.editTextContactName)
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber)
        editTextAddress = findViewById(R.id.editTextAddress)
        editTextRelationship = findViewById(R.id.editTextRelationship)
        imageViewAddContact = findViewById(R.id.imageViewAddContact)
        buttonAddContact = findViewById(R.id.buttonAddContact)
        textViewAddContact = findViewById(R.id.textViewAddContact)

    }

    private fun setListeners() {
        buttonAddContact.setOnClickListener(this)
        imageViewAddContact.setOnClickListener(this)
    }

    private fun populateViews() {
        if(intent.getBooleanExtra("edit", false)){
            editTextContactName.setText(intent.getStringExtra("name"))
            editTextPhoneNumber.setText(intent.getStringExtra("phone"))
            editTextAddress.setText(intent.getStringExtra("address"))
            editTextRelationship.setText(intent.getStringExtra("relationship"))
            imageViewAddContact.setImageDrawable(getDrawable(resources.obtainTypedArray(R.array.contact_images).getResourceId(intent.getIntExtra("index", 0), 0)) )
            buttonAddContact.setText(R.string.edit_contact)
            textViewAddContact.text = getString(R.string.edit_contact)
        }
    }


    override fun onClick(view: View?) {
        if(view?.id == R.id.buttonAddContact){
            var error = ""
            if(editTextContactName.text.toString() == ""){
                error += getString(R.string.contact_name_error) + "\n"
            }
            val regex = """^\d{10}${'$'}""".toRegex()
            if(!regex.matches(editTextPhoneNumber.text.toString())){
                error += getString(R.string.invalid_phone_number) + "\n"
            }
            if(error != "") {
                val builder = AlertDialog.Builder(context)
                builder.setTitle(R.string.error_title_contact)
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
                builder.setMessage(R.string.success_message_contact_added)
                builder.setIcon(android.R.drawable.ic_dialog_info)
                builder.setPositiveButton("Ok"){dialogInterface, which ->
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }
        if(view?.id == R.id.imageViewAddContact){
            selectImageIntent.launch("image/*")
        }
    }
}