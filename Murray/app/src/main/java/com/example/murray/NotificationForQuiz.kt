package com.example.murray

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

var notificationID_Quiz = 1
const val channel2ID = "channel2"
const val notificationTitle = "Daily quiz"
const val notificationMessage = "It's quiz time!"

class NotificationForQuiz : BroadcastReceiver()
{
    override fun onReceive(context: Context, intent: Intent)
    {
        val mainIntent = Intent(context, QuizUserActivity::class.java)
        mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val mainPendingIntent = PendingIntent.getActivity(
            context,
            1,
            mainIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
        )

        val notification = NotificationCompat.Builder(context, channel2ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(notificationTitle)
            .setContentText(notificationMessage)
            .setAutoCancel(true)
            .setContentIntent(mainPendingIntent)
            .build()

        val  manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID_Quiz, notification)
    }
}