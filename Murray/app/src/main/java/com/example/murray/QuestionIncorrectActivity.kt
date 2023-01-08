package com.example.murray

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.murray.model.QuestionModel

class QuestionIncorrectActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var questionDetailsTextView: TextView
    private lateinit var nextQuestionButton: ImageView
    private lateinit var currentQuestionNumberTextView: TextView
    private lateinit var questionList : ArrayList<Int>
    private var questionNumber: Int = 1
    private var totalCorrectQuestions: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_incorrect_question)

        initializeViews()
        populateViews()
        setListeners()
    }

    private fun initializeViews() {
        questionDetailsTextView = findViewById(R.id.textViewFailureDetails)
        nextQuestionButton = findViewById(R.id.buttonNextQuestion)
        currentQuestionNumberTextView = findViewById(R.id.textViewCurrentQuestionNumber)
    }

    private fun populateViews() {
        questionList = intent.getIntegerArrayListExtra("dataSource") as ArrayList<Int>
        questionNumber = intent.getIntExtra("questionNumber", 0)
        totalCorrectQuestions = intent.getIntExtra("totalCorrectQuestions", 0)
        currentQuestionNumberTextView.text = questionNumber.toString()
        questionDetailsTextView.text = intent.getStringExtra("questionDetails")
    }

    private fun setListeners() {
        nextQuestionButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (questionNumber == 3) {
            val intent = Intent ( this, QuizUserFinishedActivity::class.java )
            intent.putExtra("totalCorrectQuestions", totalCorrectQuestions)
            startActivity(intent)
        } else {
            val intent = Intent ( this, QuizUserActivity::class.java )
            intent.putExtra("dataSource", questionList)
            intent.putExtra("questionNumber", questionNumber + 1)
            intent.putExtra("totalCorrectQuestions", totalCorrectQuestions)
            startActivity(intent)
        }
    }
}