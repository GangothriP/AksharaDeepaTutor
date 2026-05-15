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
import com.example.aksharadeepatutor.ui.components.RadarChart
import com.example.aksharadeepatutor.ui.components.SubjectProgressRow
import com.example.aksharadeepatutor.viewmodel.DashboardViewModel

@Composable
fun StrengthScreen(viewModel: DashboardViewModel) {
    val performances by viewModel.performances.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Strength Map",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text("Radar chart shows quiz performance. If no quiz is taken, syllabus progress is used.")

        RadarChart(performances)

        performances.forEach {
            SubjectProgressRow(
                subject = it.subject,
                progress = it.progress,
                score = it.averageScore
            )
        }
    }
}
