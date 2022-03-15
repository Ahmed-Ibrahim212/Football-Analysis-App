package com.example.footballdataapp.di

import android.content.Context
import androidx.room.Room
import com.example.footballdataapp.data.local.TeamsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object LocalModule {

    @Singleton
    @Provides
    fun providesTeamsDataBase(@ApplicationContext context: Context): TeamsDatabase {
        return Room.databaseBuilder(
            context,
            TeamsDatabase::class.java,
            TeamsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
