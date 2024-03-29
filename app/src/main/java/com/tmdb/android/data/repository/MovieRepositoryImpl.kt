package com.tmdb.android.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tmdb.android.data.paging.MovieByGenrePagingSource
import com.tmdb.android.data.paging.SearchMoviesPagingSource
import com.tmdb.android.data.paging.TopRatedMoviePagingSource
import com.tmdb.android.data.remote.api.MovieApi
import com.tmdb.android.data.remote.response.GenreResponse
import com.tmdb.android.data.remote.response.ReviewResponse
import com.tmdb.android.data.remote.response.VideoResponse
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.domain.repository.MovieRepository
import com.tmdb.android.utils.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { SearchMoviesPagingSource(api, query) }
        ).flow
    }

    override fun getTopRatedMovie(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { TopRatedMoviePagingSource(api) }
        ).flow
    }

    override fun getMovieByGenre(genreId: Int): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { MovieByGenrePagingSource(api, genreId) }
        ).flow
    }

    override suspend fun getGenres(): GenreResponse {
        return api.getGenres()
    }

    override suspend fun getVideos(movieId: Int): VideoResponse {
        return api.getVideos(movieId)
    }

    override suspend fun getReviews(movieId: Int): ReviewResponse {
        return api.getReviews(movieId)
    }
}