package com.example.murray.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.murray.R
import com.example.murray.model.Contact

class ContactsPatientAdapter (private val context: Context, private val dataSource: ArrayList<Contact>) : BaseAdapter() {
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
        val rowView = inflater.inflate(R.layout.listview_item_contact_patient, viewGroupParent, false)
        rowView.findViewById<TextView>(R.id.textViewContactName).text = dataSource.get(position).name
        rowView.findViewById<ImageView>(R.id.imageViewContact).background = dataSource.get(position).image
        return rowView
    }
}