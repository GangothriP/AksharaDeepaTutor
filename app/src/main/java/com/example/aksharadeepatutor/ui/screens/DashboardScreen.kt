package com.example.aksharadeepatutor.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksharadeepatutor.navigation.Screen
import com.example.aksharadeepatutor.ui.components.SubjectProgressRow
import com.example.aksharadeepatutor.ui.components.TutorCard
import com.example.aksharadeepatutor.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    navigate: (String) -> Unit
) {
    val performances by viewModel.performances.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Akshara-Deepa Tutor",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "AI Self Study App for 10th Standard Students",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
        )

        TutorCard(
            title = "Today's Mission",
            subtitle = "Study one chapter and complete one quiz.",
            accent = Color(0xFF0F766E),
            onClick = { navigate(Screen.Goal.route) }
        )
        TutorCard(
            title = "Study Notes",
            subtitle = "Read chapter-wise notes before quiz practice.",
            accent = Color(0xFF0F766E),
            onClick = { navigate(Screen.Notes.route) }
        )


        TutorCard(
            title = "Syllabus Tracker",
            subtitle = "Track Science, Math and Social chapter completion.",
            accent = Color(0xFF2563EB),
            onClick = { navigate(Screen.Syllabus.route) }
        )

        TutorCard(
            title = "Practice Quiz",
            subtitle = "5 timed MCQ questions with answer review.",
            accent = Color(0xFFF97316),
            onClick = { navigate(Screen.Quiz.route) }
        )

        TutorCard(
            title = "Strength Map",
            subtitle = "View radar chart and subject-wise performance.",
            accent = Color(0xFF7C3AED),
            onClick = { navigate(Screen.Strength.route) }
        )

        TutorCard(
            title = "Gap Analysis",
            subtitle = "Find weak subjects and get revision suggestions.",
            accent = Color(0xFFDC2626),
            onClick = { navigate(Screen.Gap.route) }
        )

        Text(
            text = "Quick Summary",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        performances.forEach {
            SubjectProgressRow(
                subject = it.subject,
                progress = it.progress,
                score = it.averageScore
            )
        }

        Button(
            onClick = { navigate(Screen.Goal.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Open Daily Goal")
        }
    }
}
