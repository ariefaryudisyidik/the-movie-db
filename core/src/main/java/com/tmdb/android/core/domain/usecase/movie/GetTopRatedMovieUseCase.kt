package com.tmdb.android.core.domain.usecase.movie

import androidx.paging.PagingData
import com.tmdb.android.core.domain.model.Movie
import com.tmdb.android.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getTopRatedMovie()
    }
}