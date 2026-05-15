package com.example.aksharadeepatutor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksharadeepatutor.data.entity.StudentEntity
import com.example.aksharadeepatutor.repository.TutorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val student: StudentEntity? = null,
    val error: String? = null,
    val signedIn: Boolean = false
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: TutorRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.seedIfNeeded()
            repository.observeStudent().collect { student ->
                _uiState.update {
                    it.copy(
                        student = student,
                        signedIn = student != null
                    )
                }
            }
        }
    }

    fun signUp(name: String, studentClass: String, school: String, password: String) {
        if (name.isBlank() || studentClass.isBlank() || school.isBlank() || password.isBlank()) {
            _uiState.update { it.copy(error = "Please fill all fields") }
            return
        }

        viewModelScope.launch {
            repository.saveStudent(name, studentClass, school, password)
            _uiState.update { it.copy(error = null, signedIn = true) }
        }
    }

    fun signIn(name: String, password: String) {
        viewModelScope.launch {
            val valid = repository.validateSignIn(name, password)
            _uiState.update {
                if (valid) it.copy(error = null, signedIn = true)
                else it.copy(error = "Invalid name or password")
            }
        }
    }
}
