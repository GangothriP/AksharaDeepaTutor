package com.example.aksharadeepatutor.model


data class ReviewAnswer(
    val question: String,
    val selectedAnswer: String,
    val correctAnswer: String,
    val isCorrect: Boolean
)
