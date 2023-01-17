package com.tmdb.android.domain.repository

import androidx.paging.PagingData
import com.tmdb.android.data.remote.response.GenreResponse
import com.tmdb.android.data.remote.response.ReviewResponse
import com.tmdb.android.data.remote.response.VideoResponse
import com.tmdb.android.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun searchMovies(query: String): Flow<PagingData<Movie>>
    fun getTopRatedMovie(): Flow<PagingData<Movie>>
    fun getMovieByGenre(genreId: Int): Flow<PagingData<Movie>>
    suspend fun getGenres(): GenreResponse
    suspend fun getVideos(movieId: Int): VideoResponse
    suspend fun getReviews(movieId: Int): ReviewResponse
}