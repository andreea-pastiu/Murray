package com.example.murray

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.murray.adapters.MedicinesAdapter
import com.example.murray.adapters.MedicinesPatientAdapter
import com.example.murray.model.Medicine

class MedicineListPatientActivity : AppCompatActivity() {

    private lateinit var medications_listview: ListView
    private val medicineList = mutableListOf<Medicine>() as ArrayList<Medicine>
    private lateinit var medicinesPatientAdapter: MedicinesPatientAdapter

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine_list_patient)

        initializeViews()
        initializeMedicineList()
        medicinesPatientAdapter = MedicinesPatientAdapter(this, medicineList, resultLauncher)
        medications_listview.adapter = medicinesPatientAdapter
    }

    private fun initializeViews() {
        medications_listview = findViewById(R.id.medications_listview)
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
}