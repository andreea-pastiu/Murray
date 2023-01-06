package com.example.murray

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class AddMedicineActivity : AppCompatActivity(), OnClickListener {
    private lateinit var editTextMedicineName: EditText
    private lateinit var buttonDecreaseMinutes: Button
    private lateinit var textViewMinutesNo: TextView
    private lateinit var buttonIncreaseMinutes: Button
    private lateinit var buttonDecreasePills: Button
    private lateinit var textViewPillsNo: TextView
    private lateinit var buttonIncreasePills: Button
    private lateinit var editTextDetails: EditText
    private lateinit var buttonAddMedicine: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var imageViewMedicine: ImageButton
    private lateinit var tMo: ToggleButton
    private lateinit var tTu: ToggleButton
    private lateinit var tWe: ToggleButton
    private lateinit var tTh: ToggleButton
    private lateinit var tFr: ToggleButton
    private lateinit var tSa: ToggleButton
    private lateinit var tSu: ToggleButton
    private lateinit var timepicker: TimePicker
    private lateinit var textViewAddMedicine: TextView
    private var context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medicine)

        initializeViews()
        populateViews()
        setListeners()
    }

    private val selectImageIntent = registerForActivityResult(ActivityResultContracts.GetContent())
    { uri ->
        imageViewMedicine.setImageURI(uri)
    }

    private fun initializeViews() {
        editTextMedicineName = findViewById(R.id.editTextMedicineName)
        buttonDecreaseMinutes = findViewById(R.id.buttonDecreaseMinutes)
        textViewMinutesNo = findViewById(R.id.textViewMinutesNo)
        buttonIncreaseMinutes = findViewById(R.id.buttonIncreaseMinutes)
        buttonDecreasePills = findViewById(R.id.buttonDecreasePills)
        textViewPillsNo = findViewById(R.id.textViewPillsNo)
        buttonIncreasePills = findViewById(R.id.buttonIncreasePills)
        editTextDetails = findViewById(R.id.editTextDetails)
        buttonAddMedicine = findViewById(R.id.buttonAddMedicine)
        radioGroup = findViewById(R.id.radioGroup)
        imageViewMedicine = findViewById(R.id.imageViewMedicine)
        tMo = findViewById(R.id.tMo);
        tTu = findViewById(R.id.tTu);
        tWe = findViewById(R.id.tWe);
        tTh = findViewById(R.id.tTh);
        tFr = findViewById(R.id.tFr);
        tSa = findViewById(R.id.tSa);
        tSu = findViewById(R.id.tSu);
        timepicker = findViewById(R.id.timepicker)
        textViewAddMedicine = findViewById(R.id.textViewAddMedicine)

    }

    private fun populateViews() {
        if(intent.getBooleanExtra("edit", false)){
            editTextMedicineName.setText(intent.getStringExtra("name"))
            textViewMinutesNo.text = intent.getIntExtra("minutes", 0).toString()
            textViewPillsNo.text = intent.getDoubleExtra("pills", 0.0).toString()
            editTextDetails.setText(intent.getStringExtra("details"))
            if(intent.getBooleanExtra("before", false)) {
                radioGroup.check(R.id.radio_before)
            }
            else {
                radioGroup.check(R.id.radio_after)
            }
            tMo.isChecked = intent.getBooleanExtra("monday", false)
            tTu.isChecked = intent.getBooleanExtra("tuesday", false)
            tWe.isChecked = intent.getBooleanExtra("wednesday", false)
            tTh.isChecked = intent.getBooleanExtra("thursday", false)
            tFr.isChecked = intent.getBooleanExtra("friday", false)
            tSa.isChecked = intent.getBooleanExtra("saturday", false)
            tSu.isChecked = intent.getBooleanExtra("sunday", false)
            timepicker.hour = intent.getIntExtra("hour", 0)
            timepicker.minute = intent.getIntExtra("minute", 0)
            imageViewMedicine.setImageDrawable(getDrawable(resources.obtainTypedArray(R.array.medicine_image).getResourceId(intent.getIntExtra("index", 0), 0)) )
            buttonAddMedicine.setText(R.string.edit_medicine)
            textViewAddMedicine.text = getString(R.string.edit_medicine)
        }
    }

    private fun setListeners() {
        buttonDecreaseMinutes.setOnClickListener(this)
        buttonIncreaseMinutes.setOnClickListener(this)
        buttonDecreasePills.setOnClickListener(this)
        buttonIncreasePills.setOnClickListener(this)
        buttonAddMedicine.setOnClickListener(this)
        imageViewMedicine.setOnClickListener(this)
    }


    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.buttonDecreaseMinutes -> {
                var minutes = textViewMinutesNo.text.toString().toInt()
                if(minutes > 0) {
                    minutes--
                }
                textViewMinutesNo.text = minutes.toString()
            }
            R.id.buttonIncreaseMinutes -> {
                var minutes = textViewMinutesNo.text.toString().toInt()
                if(minutes < 999) {
                    minutes++
                }
                textViewMinutesNo.text = minutes.toString()
            }
            R.id.buttonDecreasePills -> {
                var pills = textViewPillsNo.text.toString().toDouble()
                if(pills > 0) {
                    pills -= 0.5
                }
                textViewPillsNo.text = pills.toString()
            }
            R.id.buttonIncreasePills -> {
                var pills = textViewPillsNo.text.toString().toDouble()
                if(pills < 99) {
                    pills += 0.5
                }
                textViewPillsNo.text = pills.toString()
            }
            R.id.buttonAddMedicine -> {
                var error = ""
                if(editTextMedicineName.text.toString() == "") {
                    error += getString(R.string.required_medicine_name) + '\n'
                }
                if(radioGroup.checkedRadioButtonId == -1) {
                    error += getString(R.string.before_after_not_checked) + '\n'
                }
                if(textViewPillsNo.text.toString().toDouble() == 0.0) {
                    error += getString(R.string.pills_not_zero) + '\n'
                }
                if(!tMo.isChecked && !tTu.isChecked && !tWe.isChecked && !tTh.isChecked && !tFr.isChecked && !tSa.isChecked && !tSu.isChecked) {
                    error += getString(R.string.no_day_selected)
                }
                if(error != "") {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle(R.string.error_title_add_medicine)
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
                    builder.setMessage(R.string.success_message_add_medicine)
                    builder.setIcon(android.R.drawable.ic_dialog_info)
                    builder.setPositiveButton("Ok"){dialogInterface, which ->
                    }
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }
            }
            R.id.imageViewMedicine -> {
                selectImageIntent.launch("image/*")
            }
        }
    }
}