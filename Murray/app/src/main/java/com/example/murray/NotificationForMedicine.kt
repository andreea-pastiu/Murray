package com.example.murray

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

var notificationID_Medicine = 1
const val channelID_Medicine = "channel3"

class NotificationForMedicine: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent)
    {
        val mainIntent = Intent(context, TakeMedicineActivity::class.java)
        mainIntent.putExtra( "name", intent?.getStringExtra("name"))
        mainIntent.putExtra( "pills", intent?.getDoubleExtra("pills", 0.0))
        mainIntent.putExtra( "minutes", intent?.getIntExtra("minutes", 0))
        mainIntent.putExtra( "before", intent?.getBooleanExtra("before", false))
        mainIntent.putExtra( "index", intent?.getIntExtra("index", 0))
        mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val mainPendingIntent = PendingIntent.getActivity(
            context,
            1,
            mainIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
        )

        val notification = NotificationCompat.Builder(context, channelID_Medicine)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("Medicine time")
            .setContentText(intent?.getStringExtra("name"))
            .setAutoCancel(true)
            .setContentIntent(mainPendingIntent)
            .build()

        val  manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID_Medicine, notification)
    }

}