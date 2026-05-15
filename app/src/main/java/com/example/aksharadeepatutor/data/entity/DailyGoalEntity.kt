package com.example.aksharadeepatutor.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_goals")
data class DailyGoalEntity(
    @PrimaryKey val date: String,
    val studiedTopic: Boolean = false,
    val completedQuiz: Boolean = false,
    val streak: Int = 0
)
