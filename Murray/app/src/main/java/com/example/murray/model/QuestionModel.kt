package com.example.murray.model

data class QuestionModel(
    val QuestionNumber: Int,
    val QuestionContent: String,
    val QuestionCorrectAnswer: String,
    val QuestionWrongAnswers: List<String>,
    val QuestionDetails: String
)
