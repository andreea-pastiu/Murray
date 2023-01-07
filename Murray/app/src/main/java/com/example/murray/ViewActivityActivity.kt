package com.example.murray

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewActivityActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var backButtonImageView: ImageView
    private lateinit var activityName: String
    private lateinit var activityDetails: String
    private lateinit var activityTime: String
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

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    private fun setData() {
        activityName = intent.getStringExtra("activity_name").toString()
        activityDetails = intent.getStringExtra("activity_details").toString()
        activityTime = intent.getStringExtra("activity_time").toString()

        textViewActivityName.text = activityName
        textViewActivityDetails.text = activityDetails
        textViewActivityTime.text = activityTime
    }
}