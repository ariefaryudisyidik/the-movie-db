package com.tmdb.android.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.tmdb.android.domain.model.Movie

interface MovieRepository {
    fun getMovie(): LiveData<PagingData<Movie>>
}