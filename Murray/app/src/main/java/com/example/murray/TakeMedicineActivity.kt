package com.example.murray

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class TakeMedicineActivity : AppCompatActivity(), OnClickListener {

    private lateinit var textViewName: TextView
    private lateinit var textViewDetails: TextView
    private lateinit var imageViewMedicine: ImageView
    private lateinit var buttonMarkAsTaken: Button
    private lateinit var buttonTakeLater: Button
    private var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_medicine)

        initializeViews()
        populateViews()
        setListeners()
    }

    private fun initializeViews() {
        textViewName = findViewById(R.id.textViewName)
        textViewDetails = findViewById(R.id.textViewDetails)
        imageViewMedicine = findViewById(R.id.imageViewMedicine)
        buttonMarkAsTaken = findViewById(R.id.buttonMarkAsTaken)
        buttonTakeLater = findViewById(R.id.buttonTakeLater)
    }

    private fun setListeners() {
        buttonMarkAsTaken.setOnClickListener(this)
        buttonTakeLater.setOnClickListener(this)
    }

    private fun populateViews() {
        textViewName.text = intent.getStringExtra("name")
        var details = "Take " + intent.getDoubleExtra("pills", 0.0) + " pills of this medicine" +
                intent.getIntExtra("minutes ", 0)
        if(intent.getBooleanExtra("before", false)){
            details += "before eating"
        }
        else{
            details += "after eating"
        }
        textViewDetails.text = details
        imageViewMedicine.setImageDrawable(getDrawable(resources.obtainTypedArray(R.array.medicine_image).getResourceId(intent.getIntExtra("index", 0), 0)) )
    }

    override fun onClick(view: View?){
        if(view?.id == R.id.buttonTakeLater){
            val builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.chose_later)
            builder.setMessage(R.string.notify_later)
            builder.setIcon(android.R.drawable.ic_dialog_info)
            builder.setPositiveButton("Ok"){dialogInterface, which ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
        if(view?.id == R.id.buttonMarkAsTaken){
            val builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.chose_take)
            builder.setMessage(R.string.pills_marked)
            builder.setIcon(android.R.drawable.ic_dialog_info)
            builder.setPositiveButton("Ok"){dialogInterface, which ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }
}