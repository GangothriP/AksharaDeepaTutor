package com.example.aksharadeepatutor.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksharadeepatutor.navigation.Screen
import com.example.aksharadeepatutor.viewmodel.QuizViewModel

@Composable
fun QuizScreen(
    viewModel: QuizViewModel,
    subject: String?,
    chapter: String?,
    navigate: (String) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(subject, chapter) {
        if (!subject.isNullOrBlank() && !chapter.isNullOrBlank()) {
            viewModel.startChapterQuiz(subject, chapter)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = if (state.selectedChapter == null) "Practice Quiz" else "${state.selectedChapter} Quiz",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        if (!state.isRunning && !state.isFinished) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                state.subjects.forEach { item ->
                    FilterChip(
                        selected = state.selectedSubject == item,
                        onClick = { viewModel.selectSubject(item) },
                        label = { Text(item) }
                    )
                }
            }

            Button(
                onClick = { viewModel.startQuiz() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Quiz")
            }

            state.error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }

        if (state.isRunning) {
            val question = state.questions[state.currentIndex]

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Question ${state.currentIndex + 1}/${state.questions.size}")
                Text(
                    text = "${state.secondsLeft}s",
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                )
            }

            LinearProgressIndicator(
                progress = { state.secondsLeft / 30f },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = question.question,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            question.options.forEach { option ->
                OutlinedButton(
                    onClick = { viewModel.answer(option) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(option)
                }
            }
        }

        if (state.isFinished) {
            val percent = if (state.questions.isEmpty()) {
                0
            } else {
                state.score * 100 / state.questions.size
            }

            Text(
                text = "Quiz Completed",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Score: ${state.score}/${state.questions.size} ($percent%)",
                style = MaterialTheme.typography.headlineSmall
            )

            Button(
                onClick = { navigate(Screen.Review.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Results")
            }

            OutlinedButton(
                onClick = { viewModel.startQuiz() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Take Another Quiz")
            }
        }
    }
}
