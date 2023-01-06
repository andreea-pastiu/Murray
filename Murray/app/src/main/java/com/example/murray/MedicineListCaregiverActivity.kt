package com.example.murray

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import com.example.murray.adapters.MedicinesAdapter
import com.example.murray.model.Medicine

class MedicineListCaregiverActivity : AppCompatActivity(), AdapterView.OnItemClickListener, OnClickListener {

    private lateinit var medications_listview: ListView
    private lateinit var buttonAddMedicine: Button
    private val medicineList = mutableListOf<Medicine>() as ArrayList<Medicine>
    private lateinit var medicinesAdapter: MedicinesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine_list_caregiver)

        initializeViews()
        initializeMedicineList()
        medicinesAdapter = MedicinesAdapter(this, medicineList)
        medications_listview.adapter = medicinesAdapter
        setListeners()
    }

    private fun initializeViews() {
        medications_listview = findViewById(R.id.medications_listview)
        buttonAddMedicine = findViewById(R.id.buttonAddMedicine)
    }

    private fun setListeners() {
        medications_listview.setOnItemClickListener(this)
        buttonAddMedicine.setOnClickListener(this)
    }

    private fun initializeMedicineList() {
        medicineList.clear()
        for (index in resources.getStringArray(R.array.medicine_names).indices) {
            getDrawable(
                resources.obtainTypedArray(R.array.medicine_image).getResourceId(index, 0)
            )?.let {
                Medicine(
                    resources.getStringArray(R.array.medicine_names)[index],
                    resources.getIntArray(R.array.medicine_minutes)[index],
                    resources.getStringArray(R.array.medicine_before)[index].toBoolean(),
                    resources.getStringArray(R.array.medicine_pills)[index].toDouble(),
                    resources.getStringArray(R.array.medicine_details)[index],
                    resources.getStringArray(R.array.medicine_monday)[index].toBoolean(),
                    resources.getStringArray(R.array.medicine_tuesday)[index].toBoolean(),
                    resources.getStringArray(R.array.medicine_wednesday)[index].toBoolean(),
                    resources.getStringArray(R.array.medicine_thursday)[index].toBoolean(),
                    resources.getStringArray(R.array.medicine_friday)[index].toBoolean(),
                    resources.getStringArray(R.array.medicine_saturday)[index].toBoolean(),
                    resources.getStringArray(R.array.medicine_sunday)[index].toBoolean(),
                    resources.getIntArray(R.array.medicine_hour)[index],
                    resources.getIntArray(R.array.medicine_minute)[index],
                    it
                )
            }?.let {
                medicineList.add(
                    it
                )
            }
        }
    }

    override fun onClick(view: View?) {
        val intent = Intent ( this, AddMedicineActivity::class.java )
        startActivity(intent)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent ( this, AddMedicineActivity::class.java )
        intent.putExtra("edit", true)
        intent.putExtra("name", medicineList[position].name)
        intent.putExtra("minutes", medicineList[position].minutes)
        intent.putExtra("before", medicineList[position].before)
        intent.putExtra("pills", medicineList[position].pills)
        intent.putExtra("details", medicineList[position].details)
        intent.putExtra("monday", medicineList[position].monday)
        intent.putExtra("tuesday", medicineList[position].tuesday)
        intent.putExtra("wednesday", medicineList[position].wednesday)
        intent.putExtra("thursday", medicineList[position].thursday)
        intent.putExtra("friday", medicineList[position].friday)
        intent.putExtra("saturday", medicineList[position].saturday)
        intent.putExtra("sunday", medicineList[position].sunday)
        intent.putExtra("hour", medicineList[position].hour)
        intent.putExtra("minute", medicineList[position].minute)
        intent.putExtra("index", position)
        startActivity(intent)
    }
}