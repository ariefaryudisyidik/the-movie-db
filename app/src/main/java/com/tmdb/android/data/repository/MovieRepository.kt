package com.tmdb.android.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.tmdb.android.data.remote.response.GenreResponse
import com.tmdb.android.domain.model.Movie

interface MovieRepository {
    fun getMovie(): LiveData<PagingData<Movie>>
    fun searchMovies(query: String): LiveData<PagingData<Movie>>
    suspend fun getGenres(): GenreResponse
}