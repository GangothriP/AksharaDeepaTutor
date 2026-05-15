package com.example.aksharadeepatutor.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentEntity(
    @PrimaryKey val id: Int = 1,
    val name: String,
    val studentClass: String,
    val school: String,
    val password: String
)
