package com.tmdb.android.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.tmdb.android.data.paging.MoviePagingSource
import com.tmdb.android.data.remote.api.MovieApi
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.utils.ITEM_PER_PAGE
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    @ExperimentalPagingApi
    override fun getMovie(): LiveData<PagingData<Movie>> =
        Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            pagingSourceFactory = { MoviePagingSource(api) }
        ).liveData
}