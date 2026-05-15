package com.example.aksharadeepatutor.navigation

import android.net.Uri

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object Auth : Screen("auth")
    data object Dashboard : Screen("dashboard")
    data object Notes : Screen("notes")
    data object Syllabus : Screen("syllabus")
    data object Quiz : Screen("quiz?subject={subject}&chapter={chapter}")
    data object Review : Screen("review")
    data object Strength : Screen("strength")
    data object Goal : Screen("goal")
    data object Gap : Screen("gap")

    companion object {
        fun quizRoute(subject: String? = null, chapter: String? = null): String {
            return if (subject == null || chapter == null) {
                "quiz"
            } else {
                "quiz?subject=${Uri.encode(subject)}&chapter=${Uri.encode(chapter)}"
            }
        }
    }
}
