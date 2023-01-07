package com.example.murray.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat.startActivity
import com.example.murray.R
import com.example.murray.TakeMedicineActivity
import com.example.murray.model.Medicine

class MedicinesPatientAdapter (private val context: Context, private val dataSource: ArrayList<Medicine>, private val resultLauncher: ActivityResultLauncher<Intent>) : BaseAdapter() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }
    override fun getItem(position: Int): Any {
        return dataSource.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView (position: Int, containerView: View?, viewGroupParent: ViewGroup?): View
    {
        val rowView = inflater.inflate(R.layout.listview_item_medicine_patient, viewGroupParent, false)
        var medicine = dataSource.get(position)
        rowView.findViewById<TextView>(R.id.textViewMedicineName).text = medicine.name
        var details = ""
        details += medicine.pills.toString() + " pills, " + medicine.minutes.toString() + " minutes "
        if(medicine.before){
            details += "before eating "
        }
        else {
            details += "after eating "
        }
        rowView.findViewById<TextView>(R.id.textviewMedicineDetails).text = details
        var details2 = ""
        if(medicine.monday && medicine.tuesday && medicine.wednesday && medicine.thursday && medicine.friday && medicine.saturday && medicine.sunday){
            details2 += "Everyday, "
        }
        else
        {
            if(medicine.monday){
                details2 += "Monday, "
            }
            if(medicine.tuesday){
                details2 += "Tuesday, "
            }
            if(medicine.wednesday){
                details2 += "Wednesday, "
            }
            if(medicine.thursday){
                details2 += "Thursday, "
            }
            if(medicine.friday){
                details2 += "Friday, "
            }
            if(medicine.saturday){
                details2 += "Saturday, "
            }
            if(medicine.sunday){
                details2 += "Sunday, "
            }
        }
        details2 += "at " + medicine.hour.toString() + ":" + medicine.minute.toString()
        rowView.findViewById<TextView>(R.id.textviewMedicineDetails2).text = details2
        rowView.findViewById<TextView>(R.id.textViewMedicineName).setOnClickListener{
            val intent = Intent ( containerView?.context, TakeMedicineActivity::class.java )
            intent.putExtra("name", medicine.name)
            intent.putExtra("pills", medicine.pills)
            intent.putExtra("before", medicine.before)
            intent.putExtra("minutes", medicine.minutes)
            intent.putExtra("index", position)
            resultLauncher.launch(intent)
        }
        return rowView
    }
}