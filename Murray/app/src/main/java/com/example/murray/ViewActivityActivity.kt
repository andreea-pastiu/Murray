package com.example.murray

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.DayOfWeek

class ViewActivityActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var backButtonImageView: ImageView
    private lateinit var activityName: String
    private lateinit var activityDetails: String
    private lateinit var activityTime: String
    private lateinit var activityDate: String
    private lateinit var activityRecurrence: ArrayList<Int>
    private lateinit var textViewActivityName: TextView
    private lateinit var textViewActivityDetails: TextView
    private lateinit var textViewActivityTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_details_activity)
        initializeViews()
        setListeners()
        setData()
    }

    private fun initializeViews() {
        textViewActivityName = findViewById(R.id.textViewActivityName)
        textViewActivityDetails = findViewById(R.id.textViewActivityDescription)
        textViewActivityTime = findViewById(R.id.textViewActivityTimeDetails)
        backButtonImageView = findViewById(R.id.imageViewBackButton)
    }

    private fun setListeners() {
        backButtonImageView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            backButtonImageView -> {
                onBackPressed()
            }
        }
    }

    private fun setData() {
        activityName = intent.getStringExtra("activity_name").toString()
        activityDetails = intent.getStringExtra("activity_description").toString()
        activityTime = intent.getStringExtra("activity_time").toString()
        activityDate = intent.getStringExtra("activity_date").toString()
        activityRecurrence = intent.extras?.get("activity_r") as ArrayList<Int>

        textViewActivityName.text = activityName
        textViewActivityDetails.text = activityDetails
        var messageString = "Do activity at " + activityTime
        if (activityRecurrence.isNotEmpty()) {
            messageString += " every "
            for (i in activityRecurrence) {
                val day = DayOfWeek.of(i)
                messageString += day.toString() + " "
            }
        } else {
            messageString += " on " + activityDate
        }
        textViewActivityTime.text = messageString
    }
}