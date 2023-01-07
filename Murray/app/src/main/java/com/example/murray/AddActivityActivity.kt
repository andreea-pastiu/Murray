package com.example.murray

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AddActivityActivity: AppCompatActivity(), View.OnClickListener {
    private lateinit var editTextActivityName: EditText
    private lateinit var editTextDescriptionActivity: EditText
    private lateinit var buttonSelectDate: Button
    private lateinit var buttonSelectTime: Button
    private var year = 0
    private  var month = 0
    private  var day = 0
    private  var hour = 0
    private  var minute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_activity)
        initializeViews()
        setListeners()
    }

    private fun initializeViews() {
        editTextActivityName = findViewById(R.id.textViewActivityName)
        editTextDescriptionActivity = findViewById(R.id.textViewActivityDescription)
        buttonSelectDate = findViewById(R.id.buttonDate)
        buttonSelectTime = findViewById(R.id.buttonTime)
    }

    private fun setListeners() {
        buttonSelectDate.setOnClickListener(this)
        buttonSelectTime.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == buttonSelectDate) {
            val c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                //txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year)
            }, year, month, day)
            datePickerDialog.show();
        }
        if (v == buttonSelectTime) {
            val c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);
            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener() { view, hourOfDay, minute ->
                //txtTime.setText(hourOfDay + ":" + minute)
            }, hour, minute, false);
            timePickerDialog.show();
        }
    }

}