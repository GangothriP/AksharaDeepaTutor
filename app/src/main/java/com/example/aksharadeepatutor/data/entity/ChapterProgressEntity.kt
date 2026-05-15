package com.example.aksharadeepatutor.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chapter_progress")
data class ChapterProgressEntity(
    @PrimaryKey val id: String,
    val subject: String,
    val chapter: String,
    val completed: Boolean = false
)
