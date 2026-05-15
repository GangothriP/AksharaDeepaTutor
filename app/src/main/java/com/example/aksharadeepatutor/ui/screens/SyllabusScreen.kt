package com.example.aksharadeepatutor.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksharadeepatutor.viewmodel.SyllabusViewModel
import kotlin.math.roundToInt

@Composable
fun SyllabusScreen(viewModel: SyllabusViewModel) {
    val chapters by viewModel.chapters.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Syllabus Tracker",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text("Tick chapters after studying. Your progress is saved offline.")

        chapters.groupBy { it.subject }.forEach { entry ->
            val subject = entry.key
            val list = entry.value
            val progress = if (list.isEmpty()) {
                0
            } else {
                ((list.count { it.completed }.toDouble() / list.size) * 100).roundToInt()
            }

            Text(
                text = "$subject - $progress%",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            LinearProgressIndicator(
                progress = { progress / 100f },
                modifier = Modifier.fillMaxWidth()
            )

            list.forEach { chapter ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = chapter.completed,
                        onCheckedChange = {
                            viewModel.toggleChapter(chapter, it)
                        }
                    )

                    Text(
                        text = chapter.chapter,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
