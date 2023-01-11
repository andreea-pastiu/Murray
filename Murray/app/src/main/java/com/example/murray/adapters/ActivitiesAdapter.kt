package com.example.murray.adapters

import android.app.AlertDialog
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import com.example.murray.R
import com.example.murray.model.Activity
import java.time.DayOfWeek

class ActivitiesAdapter(private val context: Context, private val dataSource: ArrayList<Activity>): BaseAdapter() {
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
            R.layout.list_activity_item_caregiver, p2, false
        )
        rowView.findViewById<TextView>(R.id.textViewActivityName).text = dataSource[p0].name
        val button = rowView.findViewById<ImageView>(R.id.imageViewDelete)
        button.setOnClickListener {
            alertDelete(p0)
        }
        return rowView;
    }
    private fun alertDelete(p0: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)

        builder.setCancelable(true)
        builder.setTitle("Delete")
        var messageString = "Do you want to delete the activity " + dataSource[p0].name + "?"
        builder.setMessage(messageString)

        builder.setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancel(dataSource[p0].notificationID)
            dataSource.removeAt(p0)
            notifyDataSetChanged()
        }
            .setNegativeButton("Cancel") { dialogInterface, id ->
                dialogInterface.cancel()
            }
        builder.show()
    }
}