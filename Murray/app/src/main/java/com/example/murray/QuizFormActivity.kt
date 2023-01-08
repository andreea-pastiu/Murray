package com.example.murray

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuizFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var backButtonImageView: ImageView
    private lateinit var formSubtitleTextView: TextView
    private lateinit var questionTextEditText: EditText
    private lateinit var answer1RadioButton: RadioButton
    private lateinit var answer1EditText: EditText
    private lateinit var answer2RadioButton: RadioButton
    private lateinit var answer2EditText: EditText
    private lateinit var answer3RadioButton: RadioButton
    private lateinit var answer3EditText: EditText
    private lateinit var questionDetailsEditText: EditText
    private lateinit var formButton: Button
    private var updateQuestionForm : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_question_form)

        initializeViews()
        populateViews()
        setListeners()
    }

    private fun initializeViews() {
        backButtonImageView = findViewById(R.id.imageViewBackButton)
        formSubtitleTextView = findViewById(R.id.textViewQuizSubtitle)
        questionTextEditText = findViewById(R.id.editTextEnterQuestion)
        answer1RadioButton = findViewById(R.id.radioAnswer1)
        answer1EditText = findViewById(R.id.editTextAnswer1)
        answer2RadioButton = findViewById(R.id.radioAnswer2)
        answer2EditText = findViewById(R.id.editTextAnswer2)
        answer3RadioButton = findViewById(R.id.radioAnswer3)
        answer3EditText = findViewById(R.id.editTextAnswer3)
        questionDetailsEditText = findViewById(R.id.editTextEnterDetails)
        formButton = findViewById(R.id.buttonChangePassword)
    }

    private fun populateViews() {
        if (intent.hasExtra("question_text")) {
            updateQuestionForm = true
            formSubtitleTextView.text = "Edit a question"
            questionTextEditText.setText(intent.getStringExtra("question_text").toString())
            answer1EditText.setText(intent.getStringExtra("correct_answer").toString())
            answer1RadioButton.isChecked = true
            answer2EditText.setText(intent.getStringExtra("incorrect_answer_1").toString())
            answer3EditText.setText(intent.getStringExtra("incorrect_answer_2").toString())
            questionDetailsEditText.setText(intent.getStringExtra("question_details").toString())
            formButton.text = "Update question"
        }
    }

    private fun setListeners() {
        backButtonImageView.setOnClickListener(this)
        formButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            backButtonImageView -> onBackPressed()
            formButton -> onFormButtonClicked()
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.radioAnswer1 ->
                    if (checked) {
                        // Set as correct answer
                    }
                R.id.radioAnswer2 ->
                    if (checked) {
                        // Set as correct answer
                    }
                R.id.radioAnswer3 ->
                    if (checked) {
                        // Set as correct answer
                    }
            }
        }
    }

    private fun onFormButtonClicked() {
        val dialogBinding = layoutInflater.inflate(R.layout.dialog_custom_success, null)

        val myDialog = Dialog(this)
        myDialog.setContentView(dialogBinding)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val succesText = dialogBinding.findViewById<TextView>(R.id.textViewDialogTag)
        if (updateQuestionForm) {
            succesText.text = "Question updated successfully"
        } else {
            succesText.text = "Question added successfully"
        }
        myDialog.show()
        Handler(Looper.getMainLooper()).postDelayed({
            myDialog.dismiss()
            onBackPressed()
        }, 2000)
    }
}