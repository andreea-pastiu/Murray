package com.example.murray.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.murray.AccountPasswordActivity
import com.example.murray.QuizFormActivity
import com.example.murray.R
import com.example.murray.model.QuestionModel

class QuestionAdapter(private val context: Context, private val dataSet: ArrayList<QuestionModel>) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val questionNumberTextView: TextView
        private val questionTextTextView: TextView
        private val questionEditImageView: ImageView
        private lateinit var questionCorrectAnswer: String
        private lateinit var questionIncorrectAnswer1 : String
        private lateinit var questionIncorrectAnswer2 : String
        private lateinit var questionDetails: String

        init {
            questionNumberTextView = view.findViewById(R.id.textViewQuestionNumber)
            questionTextTextView = view.findViewById(R.id.textViewQuestionText)
            questionEditImageView = view.findViewById(R.id.imageViewEditButton)
            questionEditImageView.setOnClickListener(this)
        }

        fun bindData(questionModel: QuestionModel) {
            questionNumberTextView.text = questionModel.QuestionNumber.toString()
            questionTextTextView.text = questionModel.QuestionContent
            questionCorrectAnswer = questionModel.QuestionCorrectAnswer
            questionIncorrectAnswer1 = questionModel.QuestionWrongAnswers[0]
            questionIncorrectAnswer2 = questionModel.QuestionWrongAnswers[1]
            questionDetails = questionModel.QuestionDetails
        }

        override fun onClick(p0: View?) {
            val intent = Intent (p0?.context, QuizFormActivity::class.java )
            intent.putExtra("question_text", questionTextTextView.text)
            intent.putExtra("correct_answer", questionCorrectAnswer)
            intent.putExtra("incorrect_answer_1", questionIncorrectAnswer1)
            intent.putExtra("incorrect_answer_2", questionIncorrectAnswer2)
            intent.putExtra("question_details", questionDetails)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            p0?.context?.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = inflater.inflate(R.layout.quiz_question_item_layout, parent, false)

        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bindData(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}