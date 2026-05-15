package com.example.aksharadeepatutor.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksharadeepatutor.ui.components.TutorCard
import com.example.aksharadeepatutor.viewmodel.NotesViewModel

@Composable
fun NotesScreen(
    viewModel: NotesViewModel,
    startChapterQuiz: (String, String) -> Unit
) {
    val selectedNote by viewModel.selectedNote.collectAsState()

    if (selectedNote == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "Study Notes",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Text("Open a chapter. After studying, take the related quiz.")

            viewModel.notes.groupBy { it.subject }.forEach { entry ->
                Text(
                    text = entry.key,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                entry.value.forEach { note ->
                    TutorCard(
                        title = note.chapter,
                        subtitle = "Tap to study this chapter.",
                        accent = MaterialTheme.colorScheme.primary,
                        onClick = {
                            viewModel.openNote(note)
                        }
                    )
                }
            }
        }
    } else {
        val note = selectedNote!!

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = note.chapter,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = note.subject,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyLarge
            )

            Button(
                onClick = {
                    viewModel.markCompleted(note.subject, note.chapter)
                    startChapterQuiz(note.subject, note.chapter)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Complete Study and Take Chapter Quiz")
            }

            Button(
                onClick = {
                    viewModel.closeNote()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to Notes")
            }
        }
    }
}
