package com.example.murray

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import java.util.*

class TakeMedicineActivity : AppCompatActivity(), OnClickListener {

    private lateinit var textViewName: TextView
    private lateinit var textViewDetails: TextView
    private lateinit var imageViewMedicine: ImageView
    private lateinit var buttonMarkAsTaken: Button
    private lateinit var buttonTakeLater: Button
    private var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_medicine)

        initializeViews()
        populateViews()
        setListeners()
        createNotificationChannelMedicine()
    }

    private fun initializeViews() {
        textViewName = findViewById(R.id.textViewName)
        textViewDetails = findViewById(R.id.textViewDetails)
        imageViewMedicine = findViewById(R.id.imageViewMedicine)
        buttonMarkAsTaken = findViewById(R.id.buttonMarkAsTaken)
        buttonTakeLater = findViewById(R.id.buttonTakeLater)
    }

    private fun setListeners() {
        buttonMarkAsTaken.setOnClickListener(this)
        buttonTakeLater.setOnClickListener(this)
    }

    private fun populateViews() {
        textViewName.text = intent.getStringExtra("name")
        var details = "Take " + intent.getDoubleExtra("pills ", 0.0) + " pills of this medicine " +
                intent.getIntExtra("minutes ", 0) + " minutes "
        if(intent.getBooleanExtra("before", false)){
            details += "before eating"
        }
        else{
            details += "after eating"
        }
        textViewDetails.text = details
        imageViewMedicine.setImageDrawable(getDrawable(resources.obtainTypedArray(R.array.medicine_image).getResourceId(intent.getIntExtra("index", 0), 0)) )
    }

    override fun onClick(view: View?){
        if(view?.id == R.id.buttonTakeLater){
            val builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.chose_later)
            builder.setMessage(R.string.notify_later)
            builder.setIcon(android.R.drawable.ic_dialog_info)
            builder.setPositiveButton("Ok"){dialogInterface, which ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
            scheduleNotification()
        }
        if(view?.id == R.id.buttonMarkAsTaken){
            val builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.chose_take)
            builder.setMessage(R.string.pills_marked)
            builder.setIcon(android.R.drawable.ic_dialog_info)
            builder.setPositiveButton("Ok"){dialogInterface, which ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    private fun scheduleNotification() {
        val intent = Intent(applicationContext, NotificationForMedicine::class.java)
        intent.putExtra( "name", intent.getStringExtra("name"))
        intent.putExtra( "pills", intent.getDoubleExtra("pills", 0.0))
        intent.putExtra( "minutes", intent.getIntExtra("minutes", 0))
        intent.putExtra( "before", intent.getBooleanExtra("before", false))
        intent.putExtra( "index", intent.getIntExtra("index", 0))

        notificationID_Medicine += 1
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationID_Medicine,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance()
        val time = calendar.timeInMillis + (1 * 10 * 1000)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
    }

    private fun createNotificationChannelMedicine()
    {
        val name = "Medicines Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID_Medicine, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}