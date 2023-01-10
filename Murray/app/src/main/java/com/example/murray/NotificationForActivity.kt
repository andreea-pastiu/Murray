package com.example.murray

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

var notificationID_Activity = 1
const val channelID_Activity = "channel1"

class NotificationForActivity: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        println("helllloooo")

        val intent1 = Intent(context, DoActivityActivity::class.java)
        intent1.putExtra("activity_name",intent?.getStringExtra("activity_name") )
        intent1.putExtra("activity_description", intent?.getStringExtra("activity_description"))

        val pendingIntent = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context, channelID_Activity)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("DO ACTIVITY")
            .setContentText(intent?.getStringExtra("activity_name"))
            .setContentIntent(pendingIntent)
            .build()

        val  manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID_Activity, notification)
    }

}