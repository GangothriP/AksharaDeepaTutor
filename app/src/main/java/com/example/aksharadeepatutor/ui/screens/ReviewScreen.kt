package com.example.aksharadeepatutor.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksharadeepatutor.viewmodel.QuizViewModel

@Composable
fun ReviewScreen(viewModel: QuizViewModel) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Review Answers",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        state.review.forEachIndexed { index, item ->
            val cardColor = if (item.isCorrect) {
                MaterialTheme.colorScheme.secondary.copy(alpha = 0.16f)
            } else {
                MaterialTheme.colorScheme.error.copy(alpha = 0.14f)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(cardColor, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "${index + 1}. ${item.question}",
                    fontWeight = FontWeight.Bold
                )
                Text("Your answer: ${item.selectedAnswer}")
                Text("Correct answer: ${item.correctAnswer}")
                Text(
                    text = if (item.isCorrect) "Result: Correct" else "Result: Wrong",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
