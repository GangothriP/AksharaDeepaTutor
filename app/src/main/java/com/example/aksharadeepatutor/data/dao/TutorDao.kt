package com.example.aksharadeepatutor.data.dao

import androidx.room.*
import com.example.aksharadeepatutor.data.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TutorDao {
    @Query("SELECT * FROM students WHERE id = 1 LIMIT 1")
    fun observeStudent(): Flow<StudentEntity?>

    @Query("SELECT * FROM students WHERE id = 1 LIMIT 1")
    suspend fun getStudent(): StudentEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveStudent(student: StudentEntity)

    @Query("SELECT COUNT(*) FROM questions")
    suspend fun questionCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuestionEntity>)

    @Query("SELECT * FROM questions WHERE subject = :subject")
    suspend fun getQuestionsBySubject(subject: String): List<QuestionEntity>

    @Query("SELECT * FROM questions WHERE subject = :subject AND chapter = :chapter")
    suspend fun getQuestionsByChapter(subject: String, chapter: String): List<QuestionEntity>

    @Query("SELECT * FROM chapter_progress ORDER BY subject, chapter")
    fun observeChapterProgress(): Flow<List<ChapterProgressEntity>>

    @Query("SELECT COUNT(*) FROM chapter_progress")
    suspend fun chapterCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapters(chapters: List<ChapterProgressEntity>)

    @Update
    suspend fun updateChapter(progress: ChapterProgressEntity)

    @Query("SELECT * FROM quiz_attempts ORDER BY timestamp DESC")
    fun observeQuizAttempts(): Flow<List<QuizAttemptEntity>>

    @Insert
    suspend fun insertQuizAttempt(attempt: QuizAttemptEntity)

    @Query("SELECT * FROM daily_goals WHERE date = :date LIMIT 1")
    fun observeDailyGoal(date: String): Flow<DailyGoalEntity?>

    @Query("SELECT * FROM daily_goals WHERE date = :date LIMIT 1")
    suspend fun getDailyGoal(date: String): DailyGoalEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertDailyGoal(goal: DailyGoalEntity)
}
