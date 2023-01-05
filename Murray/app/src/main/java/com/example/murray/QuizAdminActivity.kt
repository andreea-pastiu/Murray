package com.example.murray

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.murray.adapter.QuestionAdapter
import com.example.murray.model.QuestionModel

class QuizAdminActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var questionListView: RecyclerView
    private val dataSource = mutableListOf<QuestionModel>() as ArrayList<QuestionModel>
    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var backButtonImageView: ImageView
    private lateinit var addQuestionButtonImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeDatasource()
        questionAdapter = QuestionAdapter(baseContext, dataSource)
        setContentView(R.layout.activity_quiz_admin)

        initializeViews()
        setListeners()
    }

    private fun initializeViews() {
        questionListView = findViewById(R.id.recylerViewObjectList)
        questionListView.layoutManager = LinearLayoutManager(this)
        questionListView.adapter = questionAdapter
        backButtonImageView = findViewById(R.id.imageViewBackButton)
        addQuestionButtonImageView = findViewById(R.id.imageViewAddQuestion)
    }

    private fun setListeners() {
        backButtonImageView.setOnClickListener(this)
        addQuestionButtonImageView.setOnClickListener(this)
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

    override fun onClick(p0: View?) {
        when (p0) {
            backButtonImageView -> onBackPressed()
            addQuestionButtonImageView -> addQuestionButton()
        }
    }

    private fun addQuestionButton() {
        startActivity(Intent(this, QuizFormActivity::class.java))
    }
}