package com.example.murray

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.murray.model.QuestionModel
import java.util.*
import kotlin.collections.ArrayList

class QuizUserActivity : AppCompatActivity(), View.OnClickListener {

    private var dataSource = mutableListOf<QuestionModel>() as ArrayList<QuestionModel>
    private lateinit var questionList : ArrayList<Int>
    private lateinit var currentQuestionModel : QuestionModel
    private lateinit var questionNumberTextView: TextView
    private lateinit var questionTextTextView: TextView
    private lateinit var answer1Button: Button
    private lateinit var answer2Button: Button
    private lateinit var answer3Button: Button
    private lateinit var currentQuestionNumberTextView: TextView
    private var questionNumber: Int = 1
    private var totalCorrectQuestions: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quiz_user)

        initializeDatasource()
        initializeViews()
        populateViews()
        setListeners()
    }

    private fun initializeViews() {
        questionNumberTextView = findViewById(R.id.textViewQuestionNumber)
        questionTextTextView = findViewById(R.id.textViewQuestionText)
        answer1Button = findViewById(R.id.buttonAnswer1)
        answer2Button = findViewById(R.id.buttonAnswer2)
        answer3Button = findViewById(R.id.buttonAnswer3)
        currentQuestionNumberTextView = findViewById(R.id.textViewCurrentQuestionNumber)
    }

    private fun initializeDatasource() {
        dataSource.clear()
        for(index in resources.getStringArray(R.array.question_text_array).indices){
            QuestionModel(
                resources.getIntArray(R.array.question_number_array)[index],
                resources.getStringArray(R.array.question_text_array)[index],
                resources.getStringArray(R.array.question_correct_answer_array)[index],
                listOf( resources.getStringArray(R.array.question_incorrect_answer_array)[index],
                    resources.getStringArray(R.array.question_incorrect_answer_array)[index + 5]),
                resources.getStringArray(R.array.question_details_array)[index]
            ).let{
                dataSource.add(
                    it
                )
            }
        }
    }

    private fun populateViews() {
        if (intent.hasExtra("dataSource")) {
            questionList = intent.getIntegerArrayListExtra("dataSource") as ArrayList<Int>
            questionNumber = intent.getIntExtra("questionNumber", 0)
            totalCorrectQuestions = intent.getIntExtra("totalCorrectQuestions", 0)
            questionNumberTextView.text = questionNumber.toString()
            currentQuestionNumberTextView.text = questionNumber.toString()
            val currentQuestionNumber = questionList[Random().nextInt(questionList.size)]
            val questionListUpdate : ArrayList<Int> = arrayListOf()
            questionList.filterTo(questionListUpdate) { it != currentQuestionNumber }
            questionList = questionListUpdate
            currentQuestionModel = dataSource[currentQuestionNumber - 1]
        } else {
            currentQuestionModel = dataSource[Random().nextInt(dataSource.size)]
            val dataSourceUpdate : ArrayList<QuestionModel> = arrayListOf()
            dataSource.filterTo(dataSourceUpdate) {it != currentQuestionModel}
            dataSource = dataSourceUpdate
            questionList = arrayListOf()
            for (questionModel in dataSource) {
                questionList.add(questionModel.QuestionNumber)
            }
        }
        questionTextTextView.text = currentQuestionModel.QuestionContent
        var answerList: List<String> = ArrayList()
        answerList = currentQuestionModel.QuestionWrongAnswers
        answerList = answerList.plus(currentQuestionModel.QuestionCorrectAnswer)
        while (!answerList.isEmpty()) {
            val randomAnswer: String = answerList[Random().nextInt(answerList.size)]
            when (answerList.size) {
                3 -> answer1Button.text = randomAnswer
                2 -> answer2Button.text = randomAnswer
                1 -> answer3Button.text = randomAnswer
            }
            answerList = answerList.minus(randomAnswer)
        }
    }

    private fun setListeners() {
        answer1Button.setOnClickListener(this)
        answer2Button.setOnClickListener(this)
        answer3Button.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            answer1Button -> {
                if (answer1Button.text.equals(currentQuestionModel.QuestionCorrectAnswer)) {
                    val intent = Intent ( this, QuestionCorrectActivity::class.java )
                    intent.putExtra("dataSource", questionList)
                    intent.putExtra("questionNumber", questionNumber)
                    intent.putExtra("totalCorrectQuestions", totalCorrectQuestions + 1)
                    startActivity(intent)
                } else {
                    val intent = Intent ( this, QuestionIncorrectActivity::class.java )
                    intent.putExtra("dataSource", questionList)
                    intent.putExtra("questionNumber", questionNumber)
                    intent.putExtra("questionDetails", currentQuestionModel.QuestionDetails)
                    intent.putExtra("totalCorrectQuestions", totalCorrectQuestions)
                    startActivity(intent)
                }
            }
            answer2Button -> {
                if (answer2Button.text.equals(currentQuestionModel.QuestionCorrectAnswer)) {
                    Log.d("AFISARE", currentQuestionModel.QuestionDetails)
                    Log.d("AFISARE", questionNumber.toString())
                    val intent = Intent ( this, QuestionCorrectActivity::class.java )
                    intent.putExtra("dataSource", questionList)
                    intent.putExtra("questionNumber", questionNumber)
                    intent.putExtra("totalCorrectQuestions", totalCorrectQuestions + 1)
                    startActivity(intent)
                } else {
                    Log.d("AFISARE", currentQuestionModel.QuestionDetails)
                    Log.d("AFISARE", questionNumber.toString())
                    val intent = Intent ( this, QuestionIncorrectActivity::class.java )
                    intent.putExtra("dataSource", questionList)
                    intent.putExtra("questionNumber", questionNumber)
                    intent.putExtra("questionDetails", currentQuestionModel.QuestionDetails)
                    intent.putExtra("totalCorrectQuestions", totalCorrectQuestions)
                    startActivity(intent)
                }
            }
            answer3Button -> {
                if (answer3Button.text.equals(currentQuestionModel.QuestionCorrectAnswer)) {
                    val intent = Intent ( this, QuestionCorrectActivity::class.java )
                    intent.putExtra("dataSource", questionList)
                    intent.putExtra("questionNumber", questionNumber)
                    intent.putExtra("totalCorrectQuestions", totalCorrectQuestions + 1)
                    startActivity(intent)
                } else {
                    val intent = Intent ( this, QuestionIncorrectActivity::class.java )
                    intent.putExtra("dataSource", questionList)
                    intent.putExtra("questionNumber", questionNumber)
                    intent.putExtra("questionDetails", currentQuestionModel.QuestionDetails)
                    intent.putExtra("totalCorrectQuestions", totalCorrectQuestions)
                    startActivity(intent)
                }
            }
        }
    }
}