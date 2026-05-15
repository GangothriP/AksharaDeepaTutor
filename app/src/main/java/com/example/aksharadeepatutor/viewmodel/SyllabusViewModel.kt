package com.example.aksharadeepatutor.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksharadeepatutor.data.entity.ChapterProgressEntity
import com.example.aksharadeepatutor.repository.TutorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SyllabusViewModel @Inject constructor(
    private val repository: TutorRepository
) : ViewModel() {

    val chapters: StateFlow<List<ChapterProgressEntity>> =
        repository.observeChapterProgress()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    init {
        viewModelScope.launch {
            repository.seedIfNeeded()
        }
    }

    fun toggleChapter(chapter: ChapterProgressEntity, completed: Boolean) {
        viewModelScope.launch {
            repository.updateChapter(
                chapter.copy(completed = completed)
            )
        }
    }
}
