package com.example.murray.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.murray.R
import com.example.murray.model.Activity

class ActivitiesPatientAdapter(private val context: Context, private val dataSource: ArrayList<Activity>): BaseAdapter() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return dataSource.size;
    }

    override fun getItem(p0: Int): Activity {
        return dataSource[p0];
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong();
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val rowView = p1 ?: inflater.inflate(
            R.layout.list_activitiy_item_patient, p2, false
        )
        rowView.findViewById<TextView>(R.id.textViewActivityName).text = dataSource[p0].name
        return rowView;
    }

}