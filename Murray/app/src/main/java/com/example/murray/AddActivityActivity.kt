package com.example.murray

import android.Manifest.permission_group.CALENDAR
import android.app.*
import android.app.Activity.RESULT_OK
import android.app.appsearch.AppSearchResult.RESULT_OK
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SyncAdapterType
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.example.murray.databinding.ActivityMainPageBinding
import com.example.murray.model.Activity
import java.time.DayOfWeek
import java.time.DayOfWeek.of
import java.util.*
import java.util.Calendar.DAY_OF_WEEK

class AddActivityActivity: AppCompatActivity(), View.OnClickListener {

    private var activitiesList = mutableListOf<Activity>() as ArrayList<Activity>
    private lateinit var editTextActivityName: EditText
    private lateinit var editTextDescriptionActivity: EditText
    private lateinit var buttonSelectDate: ImageView
    private lateinit var buttonSelectTime: ImageView
    private lateinit var buttonSaveActivity: Button
    private lateinit var backButtonImageView: ImageView
    private var year = 0
    private var month = 0
    private var day = 0
    private var hour = 0
    private var minute = 0
    private var m_year = 0
    private var m_month = 0
    private var m_day = 0
    private var m_hour = 0
    private var m_minute = 0
    private lateinit var newActivity: Activity
    private lateinit var cbSunday: CheckBox
    private lateinit var cbMonday: CheckBox
    private lateinit var cbTuesday: CheckBox
    private lateinit var cbWednesday: CheckBox
    private lateinit var cbThursday: CheckBox
    private lateinit var cbFriday: CheckBox
    private lateinit var cbSaturday: CheckBox
    private lateinit var date: String
    private lateinit var time: String
    private lateinit var details: String
    private lateinit var name: String
    private var recurrence =  mutableListOf<Int>()
    private lateinit var status: String
    private lateinit var binding: ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_activity)
        initializeViews()
        setListeners()
    }

    private fun initializeViews() {
        editTextActivityName = findViewById(R.id.editTextActivityName)
        editTextDescriptionActivity = findViewById(R.id.editTextDescription)
        buttonSelectDate = findViewById(R.id.buttonDate)
        buttonSelectTime = findViewById(R.id.buttonTime)
        cbSunday = findViewById(R.id.checkbox_sunday)
        cbMonday = findViewById(R.id.checkbox_monday)
        cbTuesday = findViewById(R.id.checkbox_tuesday)
        cbWednesday = findViewById(R.id.checkbox_wednesday)
        cbThursday = findViewById(R.id.checkbox_thursday)
        cbFriday = findViewById(R.id.checkbox_friday)
        cbSaturday = findViewById(R.id.checkbox_saturday)
        buttonSaveActivity = findViewById(R.id.buttonSaveActivity)
        backButtonImageView = findViewById(R.id.imageViewBackButton)
    }

    private fun setListeners() {
        buttonSelectDate.setOnClickListener(this)
        buttonSelectTime.setOnClickListener(this)
        buttonSaveActivity.setOnClickListener(this)
        backButtonImageView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == backButtonImageView ) {
            onBackPressed()
        }

        if (v == buttonSelectDate) {
            val c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
            val datePickerDialog = DatePickerDialog(this, R.style.DialogTheme, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                //txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year)
                m_year = year
                m_month = monthOfYear
                m_day = dayOfMonth

                date = dayOfMonth.toString() + "-" + (monthOfYear + 1).toString() + "-" + year.toString()
            }, year, month, day)

            datePickerDialog.show();
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.GRAY);
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
        }
        if (v == buttonSelectTime) {
            val c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);
            val timePickerDialog = TimePickerDialog(this, R.style.DialogTheme, TimePickerDialog.OnTimeSetListener() { view, hourOfDay, minute ->
                //txtTime.setText(hourOfDay + ":" + minute)
                m_hour = hourOfDay
                m_minute = minute
                time = hourOfDay.toString() + ":" + minute.toString()   }, hour, minute, false);

            timePickerDialog.show();
            timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(Color.GRAY);
            timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
        }

        if (v == buttonSaveActivity ) {
            var date_alert = 0
            var time_alert = 0
            var name_alert = 0
            var details_alert = 0
            if (cbSunday.isChecked) recurrence.add(7)
            if (cbMonday.isChecked) recurrence.add(1)
            if (cbTuesday.isChecked) recurrence.add(2)
            if (cbWednesday.isChecked) recurrence.add(3)
            if (cbThursday.isChecked) recurrence.add(4)
            if (cbFriday.isChecked) recurrence.add(5)
            if (cbSaturday.isChecked) recurrence.add(6)


            name = editTextActivityName.text.toString()
            details = editTextDescriptionActivity.text.toString()
            status = "TO DO"
            activitiesList = intent.extras?.get("activities") as ArrayList<Activity>

            if (name.isEmpty()) {
                name_alert = 1
            }

            if (details.isEmpty()) {
                details_alert = 1
            }

            if (!this::time.isInitialized) {
                time_alert = 1
            }

            if (recurrence.isEmpty() && !this::date.isInitialized) {
                    date_alert = 1;
            }
            
            if (name_alert == 1 || details_alert == 1 || time_alert == 1 || date_alert == 1) {
                alert(name_alert, details_alert, time_alert, date_alert)
            } else {
                alert_add_activity()
            }

        }

    }

    private fun scheduleNotification()
    {
        val intent = Intent(this, NotificationForActivity::class.java)
        val activityN = editTextActivityName.text.toString()
        val activityD = editTextDescriptionActivity.text.toString()
        intent.putExtra( "activity_name", activityN)
        intent.putExtra( "activity_description", activityD)


        val pendingIntent = PendingIntent.getBroadcast(
            this,
            notificationID_Activity,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        notificationID_Activity += 1
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
    }

    private fun scheduleNotificationRepeating(recurrence: MutableList<Int> )
    {
        println(recurrence[0])
        val intent = Intent(this, NotificationForActivity::class.java)
        val activityN = editTextActivityName.text.toString()
        val activityD = editTextDescriptionActivity.text.toString()
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

        for (i in recurrence) {
            println(i)
            val time = getTimeRepeating(i)
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time,AlarmManager.INTERVAL_DAY * 7, pendingIntent);
        }

    }

    private fun getTime(): Long
    {
        println(m_year)
        println(m_month)
        println(m_day)
        println(m_hour)
        println(m_minute)
        val calendar = Calendar.getInstance()
        calendar.set(m_year, m_month, m_day, m_hour, m_minute)
        return calendar.timeInMillis
    }

    private fun getTimeRepeating(dayOfWeek: Int): Long
    {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        calendar.set(Calendar.HOUR, m_hour)
        calendar.set(Calendar.MINUTE, m_minute)
        return calendar.timeInMillis
    }

    private fun alert(name_alert: Int, details_alert: Int, time_alert: Int, date_alert: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@AddActivityActivity)

        builder.setCancelable(true)
        builder.setTitle("Upsi...")
        var messageString = "You left the following fields empty: \n"
        
        if (name_alert == 1) {
            messageString += "Activity name \n"
        }
        if (details_alert == 1) {
            messageString += "Activity details \n"
        }
        if (time_alert == 1) {
            messageString += "Activity time \n"
        }
        if (date_alert == 1) {
            messageString += "Activity date \n"
        }
        builder.setMessage(messageString)

        builder.setNegativeButton("Ok",
            DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.cancel() })
        builder.show()
    }
    private fun alert_add_activity() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@AddActivityActivity)

        builder.setCancelable(true)
        builder.setTitle("New Activity")
        var messageString = "You will create an activity with the name " + name + " that has the description " +
                                details + " and happens at " + time;
        if (recurrence.isNotEmpty()) {
            messageString += " every "
            for (i in recurrence) {
                val day = of(i)
                messageString += day.toString() + " "
            }
        } else {
            messageString += " on " + date
        }
        builder.setMessage(messageString)

        builder.setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
            if (recurrence.isEmpty()) {
                    activitiesList.add(Activity(name, details, time, status, date, recurrence, notificationID_Activity))
                    scheduleNotification()
            } else {
                activitiesList.add(Activity(name, details, time, status, "no date", recurrence, notificationID_Activity))
                scheduleNotificationRepeating(recurrence)
            }
            val resultIntent = Intent()
            resultIntent.putExtra("activities", activitiesList)
            setResult(1, resultIntent)
            finish()
        }
            // A "Cancel" button that does nothing
            .setNegativeButton("Cancel") { dialogInterface, id ->
                dialogInterface.cancel()
            }
        builder.show()
    }
}