package com.tmdb.android.domain.usecase.movie

import androidx.paging.PagingData
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieByGenreUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(genreId: Int): Flow<PagingData<Movie>> {
        return repository.getMovieByGenre(genreId)
    }
}