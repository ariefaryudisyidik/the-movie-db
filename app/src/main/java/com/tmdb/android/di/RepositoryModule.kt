package com.tmdb.android.di

import com.tmdb.android.data.local.room.MovieDatabase
import com.tmdb.android.data.remote.api.MovieApi
import com.tmdb.android.data.repository.MovieRepositoryImpl
import com.tmdb.android.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: MovieApi,
        database: MovieDatabase
    ): MovieRepository {
        return MovieRepositoryImpl(api, database)
    }
}