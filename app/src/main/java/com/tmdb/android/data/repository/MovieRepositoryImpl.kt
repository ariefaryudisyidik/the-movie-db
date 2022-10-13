package com.tmdb.android.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.tmdb.android.data.local.MovieDatabase
import com.tmdb.android.data.remote.ApiService
import com.tmdb.android.data.remote.MovieRemoteMediator
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val database: MovieDatabase,
    private val api: ApiService
) : MovieRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getMovie(): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = MovieRemoteMediator(database, api),
            pagingSourceFactory = {
                database.movieDao().getAllMovie()
            }
        ).liveData
    }
}