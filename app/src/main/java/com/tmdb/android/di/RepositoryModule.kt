package com.tmdb.android.di

import com.tmdb.android.data.remote.api.MovieApi
import com.tmdb.android.data.repository.MovieRepository
import com.tmdb.android.data.repository.MovieRepositoryImpl
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
        api: MovieApi
    ): MovieRepository {
        return MovieRepositoryImpl(api)
    }
}