package com.example.murray

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class DoActivityActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var activityName: String
    private lateinit var activityDetails: String
    private lateinit var textViewActivityName: TextView
    private lateinit var textViewActivityDetails: TextView
    private lateinit var buttonActivityDone: Button
    private lateinit var buttonActivityLater: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do_activity)
        initializeViews()
        setListeners()
        setData()
    }

    private fun initializeViews() {
        textViewActivityName = findViewById(R.id.textViewActivityName)
        textViewActivityDetails = findViewById(R.id.textViewActivityDescription)
        buttonActivityDone = findViewById(R.id.buttonActivityDone)
        buttonActivityLater = findViewById(R.id.buttonActivityLater)
    }

    private fun setListeners() {
        buttonActivityLater.setOnClickListener(this)
        buttonActivityDone.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            buttonActivityDone -> {
                finish()
            }
            buttonActivityLater -> {
                scheduleNotification()
            }
        }
    }

    private fun scheduleNotification()
    {
        val intent = Intent(this, NotificationForActivity::class.java)
        val activityN = textViewActivityName.text.toString()
        val activityD = textViewActivityDetails.text.toString()
        intent.putExtra( "activity_name", activityN)
        intent.putExtra( "activity_description", activityD)

        notificationID_Activity += 1
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            notificationID_Activity,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = SystemClock.elapsedRealtime() + (5 * 60 * 1000)
        println(time)
        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, time, pendingIntent)
    }

    private fun setData() {
        activityName = intent.getStringExtra("activity_name").toString()
        activityDetails = intent.getStringExtra("activity_description").toString()

        textViewActivityName.text = activityName
        textViewActivityDetails.text = activityDetails
    }
}