package com.tmdb.android.di

import com.tmdb.android.data.local.MovieDatabase
import com.tmdb.android.data.remote.ApiService
import com.tmdb.android.data.repository.MovieRepositoryImpl
import com.tmdb.android.domain.repository.MovieRepository
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
    fun provideMovieRepository(database: MovieDatabase, api: ApiService): MovieRepository {
        return MovieRepositoryImpl(database, api)
    }
}