package com.example.aksharadeepatutor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksharadeepatutor.model.TopicNote
import com.example.aksharadeepatutor.repository.TutorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: TutorRepository
) : ViewModel() {

    private val _selectedNote = MutableStateFlow<TopicNote?>(null)
    val selectedNote: StateFlow<TopicNote?> = _selectedNote.asStateFlow()

    val notes: List<TopicNote> = repository.notes

    fun openNote(note: TopicNote) {
        _selectedNote.value = note
    }

    fun closeNote() {
        _selectedNote.value = null
    }

    fun markCompleted(subject: String, chapter: String) {
        viewModelScope.launch {
            repository.markChapterCompleted(subject, chapter)
        }
    }
}
