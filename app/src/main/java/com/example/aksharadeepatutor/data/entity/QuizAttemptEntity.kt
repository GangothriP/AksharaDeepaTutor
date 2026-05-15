package com.example.aksharadeepatutor.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_attempts")
data class QuizAttemptEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val subject: String,
    val score: Int,
    val total: Int,
    val timestamp: Long = System.currentTimeMillis()
)
