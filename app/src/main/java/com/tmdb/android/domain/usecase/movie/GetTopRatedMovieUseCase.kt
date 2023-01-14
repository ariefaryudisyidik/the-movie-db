package com.tmdb.android.domain.usecase.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.domain.repository.MovieRepository
import javax.inject.Inject

class GetTopRatedMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): LiveData<PagingData<Movie>> {
        return repository.getTopRatedMovie()
    }
}