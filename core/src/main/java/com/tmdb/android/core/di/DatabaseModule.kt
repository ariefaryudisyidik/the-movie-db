package com.tmdb.android.core.di

import android.content.Context
import androidx.room.Room
import com.tmdb.android.core.data.local.database.MovieDatabase
import com.tmdb.android.core.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        DATABASE_NAME
    ).build()
}