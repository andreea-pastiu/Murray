package com.example.murray

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class MainPageActivity: AppCompatActivity(), View.OnClickListener {
    private lateinit var backButtonImageView: ImageView
    private lateinit var homeButton: CardView
    private lateinit var locationsButton: CardView
    private lateinit var quizButton: CardView
    private lateinit var medicationButton: CardView
    private lateinit var activitiesButton: CardView
    private lateinit var shoppingButton: CardView
    private lateinit var contactsButton: CardView
    private lateinit var accountType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        accountType = intent.getStringExtra("type").toString()
        initializeViews()
        setListeners()
    }

    private fun initializeViews() {
        homeButton = findViewById(R.id.cardViewHome)
        locationsButton = findViewById(R.id.cardViewLocations)
        quizButton = findViewById(R.id.cardViewQuiz)
        medicationButton = findViewById(R.id.cardViewMedication)
        activitiesButton = findViewById(R.id.cardViewActivities)
        shoppingButton = findViewById(R.id.cardViewShopping)
        contactsButton = findViewById(R.id.cardViewContacts)
        backButtonImageView = findViewById(R.id.imageViewBackButton)

    }

    private fun setListeners() {
        backButtonImageView.setOnClickListener(this)
        homeButton.setOnClickListener(this)
        locationsButton.setOnClickListener(this)
        quizButton.setOnClickListener(this)
        medicationButton.setOnClickListener(this)
        activitiesButton.setOnClickListener(this)
        shoppingButton.setOnClickListener(this)
        contactsButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.imageViewBackButton -> {

            }
            R.id.cardViewHome -> {
                if (accountType == "caregiver") {

                }
                else {

                }
            }
            R.id.cardViewLocations -> {
                if (accountType == "caregiver") {

                }
                else {

                }
            }
            R.id.cardViewQuiz -> {
                if (accountType == "caregiver") {

                }
                else {

                }
            }
            R.id.cardViewMedication -> {
                if (accountType == "caregiver") {

                }
                else {

                }
            }
            R.id.cardViewActivities -> {
                if (accountType == "caregiver") {

                }
                else {

                }
            }
            R.id.cardViewShopping -> {
                if (accountType == "caregiver") {

                }
                else {

                }
            }
            R.id.cardViewContacts -> {
                if (accountType == "caregiver") {

                }
                else {

                }
            }
            else -> {}
        }
    }


}