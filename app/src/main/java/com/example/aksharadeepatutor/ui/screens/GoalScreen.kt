package com.example.aksharadeepatutor.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksharadeepatutor.viewmodel.GoalViewModel

@Composable
fun GoalScreen(viewModel: GoalViewModel) {
    val goal by viewModel.goal.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(
            text = "Daily Goal",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text("Complete both tasks to maintain your streak.")

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = goal.studiedTopic,
                onCheckedChange = viewModel::setStudiedTopic
            )
            Text("Studied one chapter")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = goal.completedQuiz,
                onCheckedChange = viewModel::setCompletedQuiz
            )
            Text("Completed one quiz")
        }

        Text(
            text = "Current streak: ${goal.streak} day(s)",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = if (goal.studiedTopic && goal.completedQuiz) {
                "Goal completed for today."
            } else {
                "Finish both tasks to complete today's goal."
            }
        )
    }
}
