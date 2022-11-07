package com.tmdb.android.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.tmdb.android.data.remote.response.GenreResponse
import com.tmdb.android.data.remote.response.ReviewResponse
import com.tmdb.android.data.remote.response.VideoResponse
import com.tmdb.android.domain.model.Movie

interface MovieRepository {
    fun searchMovies(query: String): LiveData<PagingData<Movie>>
    fun getTopRatedMovie(): LiveData<PagingData<Movie>>
    fun getMovieByGenre(genreId: Int): LiveData<PagingData<Movie>>
    suspend fun getGenres(): GenreResponse
    suspend fun getVideos(movieId: Int): VideoResponse
    suspend fun getReviews(movieId: Int): ReviewResponse
}