package com.example.murray

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.murray.model.QuestionModel
import java.util.ArrayList

class QuizUserFinishedActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var endQuizButton: ImageView
    private lateinit var totalCorrectQuestionsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quiz_finished)

        initializeViews()
        populateViews()
        setListeners()
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
}