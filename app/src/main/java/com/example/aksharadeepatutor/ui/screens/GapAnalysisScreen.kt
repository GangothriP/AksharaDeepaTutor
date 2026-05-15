package com.example.aksharadeepatutor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksharadeepatutor.ui.components.TutorCard
import com.example.aksharadeepatutor.viewmodel.DashboardViewModel

@Composable
fun GapAnalysisScreen(viewModel: DashboardViewModel) {
    val performances by viewModel.performances.collectAsState()
    val weakSubjects = performances.filter {
        it.isWeak || it.progress < 50
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Gap Analysis",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text("Suggestions are based on syllabus completion and quiz performance.")

        if (weakSubjects.isEmpty()) {
            TutorCard(
                title = "No major gap found",
                subtitle = "Keep revising notes and attempt quizzes regularly.",
                accent = MaterialTheme.colorScheme.secondary
            )
        } else {
            weakSubjects.forEach { item ->
                TutorCard(
                    title = item.subject,
                    subtitle = "Revise notes, complete pending chapters, and take two practice quizzes. Syllabus: ${item.progress}%, Quiz: ${item.averageScore}%.",
                    accent = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
