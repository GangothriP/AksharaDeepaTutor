package com.example.aksharadeepatutor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksharadeepatutor.data.entity.ChapterProgressEntity
import com.example.aksharadeepatutor.data.entity.QuizAttemptEntity
import com.example.aksharadeepatutor.model.SubjectPerformance
import com.example.aksharadeepatutor.repository.TutorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: TutorRepository
) : ViewModel() {

    val subjects = repository.subjects

    val performances: StateFlow<List<SubjectPerformance>> =
        combine(
            repository.observeChapterProgress(),
            repository.observeQuizAttempts()
        ) { progress, attempts ->
            buildPerformance(progress, attempts)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    init {
        viewModelScope.launch {
            repository.seedIfNeeded()
        }
    }

    private fun buildPerformance(
        progress: List<ChapterProgressEntity>,
        attempts: List<QuizAttemptEntity>
    ): List<SubjectPerformance> {
        return subjects.map { subject ->
            val subjectChapters = progress.filter { it.subject == subject }
            val completed = subjectChapters.count { it.completed }

            val progressPercent = if (subjectChapters.isEmpty()) {
                0
            } else {
                ((completed.toDouble() / subjectChapters.size) * 100).roundToInt()
            }

            val subjectAttempts = attempts.filter { it.subject == subject }

            val averageScore = if (subjectAttempts.isEmpty()) {
                0
            } else {
                subjectAttempts
                    .map { ((it.score.toDouble() / it.total) * 100).roundToInt() }
                    .average()
                    .roundToInt()
            }

            SubjectPerformance(
                subject = subject,
                progress = progressPercent,
                averageScore = averageScore,
                isWeak = averageScore in 1..49 || progressPercent < 50
            )
        }
    }
}
