package com.tmdb.android.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.tmdb.android.data.paging.SearchMoviesPagingSource
import com.tmdb.android.data.paging.TopRatedMoviePagingSource
import com.tmdb.android.data.remote.api.MovieApi
import com.tmdb.android.data.remote.response.GenreResponse
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.utils.ITEM_PER_PAGE
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    @ExperimentalPagingApi
    override fun getMovie(): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            pagingSourceFactory = { TopRatedMoviePagingSource(api) }
        ).liveData
    }

    override fun searchMovies(query: String): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            pagingSourceFactory = { SearchMoviesPagingSource(api, query) }
        ).liveData
    }

    override suspend fun getGenres(): GenreResponse {
        return api.getGenres()
    }
}