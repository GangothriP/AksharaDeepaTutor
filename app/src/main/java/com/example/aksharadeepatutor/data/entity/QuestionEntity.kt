package com.example.aksharadeepatutor.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val subject: String,
    val chapter: String
)
