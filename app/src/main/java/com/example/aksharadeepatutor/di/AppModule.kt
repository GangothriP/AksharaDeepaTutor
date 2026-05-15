package com.example.aksharadeepatutor.di

import android.content.Context
import androidx.room.Room
import com.example.aksharadeepatutor.data.dao.TutorDao
import com.example.aksharadeepatutor.data.db.TutorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): TutorDatabase {
        return Room.databaseBuilder(
            context,
            TutorDatabase::class.java,
            "akshara_deepa.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideTutorDao(database: TutorDatabase): TutorDao {
        return database.tutorDao()
    }
}
