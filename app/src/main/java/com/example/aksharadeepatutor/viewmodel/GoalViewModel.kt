package com.example.aksharadeepatutor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksharadeepatutor.data.entity.DailyGoalEntity
import com.example.aksharadeepatutor.repository.TutorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val repository: TutorRepository
) : ViewModel() {

    private val today = repository.today()

    val goal: StateFlow<DailyGoalEntity> =
        repository.observeDailyGoal(today)
            .map { goal ->
                goal ?: DailyGoalEntity(date = today)
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                DailyGoalEntity(date = today)
            )

    init {
        viewModelScope.launch {
            repository.seedIfNeeded()
        }
    }

    fun setStudiedTopic(value: Boolean) {
        viewModelScope.launch {
            repository.setDailyStudiedTopic(today, value)
        }
    }

    fun setCompletedQuiz(value: Boolean) {
        viewModelScope.launch {
            repository.setDailyCompletedQuiz(today, value)
        }
    }
}
