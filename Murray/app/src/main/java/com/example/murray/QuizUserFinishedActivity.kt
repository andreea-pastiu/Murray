package com.example.murray

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.murray.model.QuestionModel
import java.time.LocalDate
import java.util.*

class QuizUserFinishedActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var endQuizButton: ImageView
    private lateinit var totalCorrectQuestionsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quiz_finished)

        initializeViews()
        populateViews()
        setListeners()
        createNotificationChannel()
        scheduleNotification()
    }

    private fun initializeViews() {
        totalCorrectQuestionsTextView = findViewById(R.id.textViewTotalCorrectQuestions)
        endQuizButton = findViewById(R.id.buttonEndQuiz)
    }

    private fun populateViews() {
        totalCorrectQuestionsTextView.text = intent.getIntExtra("totalCorrectQuestions", 0).toString()
    }

    private fun setListeners() {
        endQuizButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        //TODO("Return to main page")
        val intent = Intent ( this, LoginActivity::class.java )
        startActivity(intent)
    }

    private fun scheduleNotification() {
        val intent = Intent(applicationContext, NotificationForQuiz::class.java)

        notificationID_Quiz += 1
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationID_Quiz,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
    }

    private fun showAlert(time: Long)
    {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(applicationContext)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext)

        AlertDialog.Builder(this)
            .setTitle("Notification Scheduled")
            .setMessage(
                "\nAt: " + dateFormat.format(date) + " " + timeFormat.format(date))
            .setPositiveButton("Okay"){_,_ ->}
            .show()
    }

    private fun getTime() : Long {
        val minute = kotlin.random.Random.nextInt(0, 59)
        val hour = kotlin.random.Random.nextInt(9, 18)
        val currentDate = LocalDate.now()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.DAY_OF_MONTH, currentDate.dayOfMonth + 1)
        calendar.set(Calendar.MONTH, currentDate.monthValue - 1)
        return calendar.timeInMillis
    }

    private fun createNotificationChannel() {
        val name = "Notification Channel"
        val desc = "Channel to send informatory notification to announce a patient to take the daily quiz."
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channel2ID, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}