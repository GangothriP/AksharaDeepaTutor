package com.example.aksharadeepatutor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksharadeepatutor.data.entity.QuestionEntity
import com.example.aksharadeepatutor.model.ReviewAnswer
import com.example.aksharadeepatutor.repository.TutorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val QUIZ_SIZE = 5
private const val QUESTION_SECONDS = 30

data class QuizUiState(
    val subjects: List<String> = emptyList(),
    val selectedSubject: String = "Science",
    val selectedChapter: String? = null,
    val questions: List<QuestionEntity> = emptyList(),
    val currentIndex: Int = 0,
    val score: Int = 0,
    val secondsLeft: Int = QUESTION_SECONDS,
    val isRunning: Boolean = false,
    val isFinished: Boolean = false,
    val review: List<ReviewAnswer> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: TutorRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        QuizUiState(subjects = repository.subjects)
    )
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    private val usedQuestionIds = mutableMapOf<String, MutableSet<Int>>()
    private var timerJob: Job? = null

    init {
        viewModelScope.launch {
            repository.seedIfNeeded()
        }
    }

    fun selectSubject(subject: String) {
        _uiState.update {
            it.copy(
                selectedSubject = subject,
                selectedChapter = null,
                isFinished = false,
                isRunning = false,
                error = null
            )
        }
    }

    fun startQuiz() {
        viewModelScope.launch {
            val subject = _uiState.value.selectedSubject
            val allQuestions = repository.getQuestions(subject).shuffled()
            startFromQuestionBank(
                subject = subject,
                chapter = null,
                allQuestions = allQuestions
            )
        }
    }

    fun startChapterQuiz(subject: String, chapter: String) {
        viewModelScope.launch {
            val chapterQuestions = repository
                .getQuestionsByChapter(subject, chapter)
                .shuffled()

            if (chapterQuestions.isEmpty()) {
                _uiState.update {
                    it.copy(
                        error = "No quiz questions found for $chapter",
                        selectedSubject = subject,
                        selectedChapter = chapter,
                        isRunning = false,
                        isFinished = false
                    )
                }
                return@launch
            }

            _uiState.value = QuizUiState(
                subjects = repository.subjects,
                selectedSubject = subject,
                selectedChapter = chapter,
                questions = chapterQuestions.take(QUIZ_SIZE),
                currentIndex = 0,
                score = 0,
                secondsLeft = QUESTION_SECONDS,
                isRunning = true,
                isFinished = false,
                review = emptyList(),
                error = null
            )

            startTimer()
        }
    }

    private fun startFromQuestionBank(
        subject: String,
        chapter: String?,
        allQuestions: List<QuestionEntity>
    ) {
        val used = usedQuestionIds.getOrPut(subject) {
            mutableSetOf()
        }

        if (allQuestions.size - used.size < QUIZ_SIZE) {
            used.clear()
        }

        val pickedQuestions = allQuestions
            .filterNot { it.id in used }
            .take(QUIZ_SIZE)

        if (pickedQuestions.isEmpty()) {
            _uiState.update {
                it.copy(error = "No questions found for $subject")
            }
            return
        }

        used.addAll(pickedQuestions.map { it.id })

        _uiState.value = QuizUiState(
            subjects = repository.subjects,
            selectedSubject = subject,
            selectedChapter = chapter,
            questions = pickedQuestions,
            currentIndex = 0,
            score = 0,
            secondsLeft = QUESTION_SECONDS,
            isRunning = true,
            isFinished = false,
            review = emptyList(),
            error = null
        )

        startTimer()
    }

    fun answer(option: String) {
        val state = _uiState.value
        val question = state.questions.getOrNull(state.currentIndex) ?: return
        val correct = option == question.correctAnswer

        moveNext(
            reviewAnswer = ReviewAnswer(
                question = question.question,
                selectedAnswer = option,
                correctAnswer = question.correctAnswer,
                isCorrect = correct
            ),
            scoreDelta = if (correct) 1 else 0
        )
    }

    private fun skipByTimeout() {
        val state = _uiState.value
        val question = state.questions.getOrNull(state.currentIndex) ?: return

        moveNext(
            reviewAnswer = ReviewAnswer(
                question = question.question,
                selectedAnswer = "Not answered",
                correctAnswer = question.correctAnswer,
                isCorrect = false
            ),
            scoreDelta = 0
        )
    }

    private fun moveNext(
        reviewAnswer: ReviewAnswer,
        scoreDelta: Int
    ) {
        timerJob?.cancel()

        val nextIndex = _uiState.value.currentIndex + 1
        val newScore = _uiState.value.score + scoreDelta
        val newReview = _uiState.value.review + reviewAnswer

        if (nextIndex >= _uiState.value.questions.size) {
            finishQuiz(newScore, newReview)
        } else {
            _uiState.update {
                it.copy(
                    currentIndex = nextIndex,
                    score = newScore,
                    review = newReview,
                    secondsLeft = QUESTION_SECONDS
                )
            }

            startTimer()
        }
    }

    private fun finishQuiz(
        finalScore: Int,
        finalReview: List<ReviewAnswer>
    ) {
        viewModelScope.launch {
            timerJob?.cancel()

            val state = _uiState.value

            repository.saveQuizAttempt(
                subject = state.selectedSubject,
                score = finalScore,
                total = state.questions.size
            )

            repository.setDailyCompletedQuiz(
                repository.today(),
                true
            )

            _uiState.update {
                it.copy(
                    score = finalScore,
                    review = finalReview,
                    isRunning = false,
                    isFinished = true
                )
            }
        }
    }

    private fun startTimer() {
        timerJob?.cancel()

        timerJob = viewModelScope.launch {
            for (time in QUESTION_SECONDS downTo 0) {
                _uiState.update {
                    it.copy(secondsLeft = time)
                }

                delay(1000)
            }

            skipByTimeout()
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
