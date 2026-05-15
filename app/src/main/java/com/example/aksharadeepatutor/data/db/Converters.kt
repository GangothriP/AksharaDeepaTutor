package com.example.aksharadeepatutor.data.db

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromOptions(value: List<String>): String = value.joinToString("|~|")

    @TypeConverter
    fun toOptions(value: String): List<String> =
        if (value.isBlank()) emptyList() else value.split("|~|")
}
