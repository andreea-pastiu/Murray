package com.example.murray

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class MainPageActivity: AppCompatActivity(), View.OnClickListener {
    private lateinit var homeButton: CardView
    private lateinit var locationsButton: CardView
    private lateinit var quizButton: CardView
    private lateinit var medicationButton: CardView
    private lateinit var activitiesButton: CardView
    private lateinit var shoppingButton: CardView
    private lateinit var contactsButton: CardView
    private lateinit var accountType: String
    private lateinit var accountButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        accountType = intent.getStringExtra("type").toString()
        initializeViews()
        setListeners()
        createNotificationChannel()

    }

    private fun initializeViews() {
        homeButton = findViewById(R.id.cardViewHome)
        locationsButton = findViewById(R.id.cardViewLocations)
        quizButton = findViewById(R.id.cardViewQuiz)
        medicationButton = findViewById(R.id.cardViewMedication)
        activitiesButton = findViewById(R.id.cardViewActivities)
        shoppingButton = findViewById(R.id.cardViewShopping)
        contactsButton = findViewById(R.id.cardViewContacts)
        accountButton = findViewById(R.id.imageViewAccountButton)

    }

    private fun setListeners() {
        homeButton.setOnClickListener(this)
        locationsButton.setOnClickListener(this)
        quizButton.setOnClickListener(this)
        medicationButton.setOnClickListener(this)
        activitiesButton.setOnClickListener(this)
        shoppingButton.setOnClickListener(this)
        contactsButton.setOnClickListener(this)
        accountButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            homeButton -> {
                if (accountType == "caregiver") {
                    val intent = Intent(this,PatientHomeCaregiverActivity::class.java)
                    startActivity(intent)
                }
                else {
                    val intent = Intent(this,MyHouseActivity::class.java)
                    startActivity(intent)
                }
            }
            locationsButton -> {
                if (accountType == "caregiver") {
                    val intent = Intent(this, LocationAdminActivity::class.java)
                    startActivity(intent)
                }
                else {
                    val intent = Intent(this, LocationPatientActivity::class.java)
                    startActivity(intent)
                }
            }
            quizButton -> {
                if (accountType == "caregiver") {
                    val intent = Intent(this, QuizAdminActivity::class.java)
                    startActivity(intent)
                }
                else {
                    val intent = Intent(this, QuizUserActivity::class.java)
                    startActivity(intent)
                }
            }
            medicationButton -> {
                if (accountType == "caregiver") {
                    val intent = Intent(this,MedicineListCaregiverActivity::class.java)
                    startActivity(intent)
                }
                else {
                    val intent = Intent(this,MedicineListPatientActivity::class.java)
                    startActivity(intent)
                }
            }
            activitiesButton -> {
                if (accountType == "caregiver") {
                    val intent = Intent(this,ViewListActivitiesCaregiverActivity::class.java)
                    startActivity(intent)
                }
                else {
                    val intent = Intent(this,ViewListActivitiesPatientActivity::class.java)
                    startActivity(intent)
                }

            }
           shoppingButton -> {
                val intent = Intent(this,ViewShoppingListActivity::class.java)
                startActivity(intent)
            }
            contactsButton -> {
                if (accountType == "caregiver") {
                    val intent = Intent(this,ContactListCaregiverActivity::class.java)
                    startActivity(intent)
                }
                else {
                    val intent = Intent(this,ContactListPatientActivity::class.java)
                    startActivity(intent)
                }
            }
            accountButton -> {
                val intent = Intent(this, AccountActivity::class.java)
                startActivity(intent)
            }
            else -> {}
        }
    }

    private fun createNotificationChannel()
    {
        val name = "Activities Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID_Activity, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}