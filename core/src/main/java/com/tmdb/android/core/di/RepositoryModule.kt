package com.tmdb.android.core.di

import com.tmdb.android.core.data.remote.api.MovieApi
import com.tmdb.android.core.data.repository.MovieRepositoryImpl
import com.tmdb.android.core.domain.repository.MovieRepository
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