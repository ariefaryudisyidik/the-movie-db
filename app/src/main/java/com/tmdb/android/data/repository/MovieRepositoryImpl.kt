package com.tmdb.android.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.tmdb.android.data.paging.MovieByGenrePagingSource
import com.tmdb.android.data.paging.SearchMoviesPagingSource
import com.tmdb.android.data.paging.TopRatedMoviePagingSource
import com.tmdb.android.data.remote.api.MovieApi
import com.tmdb.android.data.remote.response.GenreResponse
import com.tmdb.android.data.remote.response.VideoResponse
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.utils.PAGE_SIZE
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override fun searchMovies(query: String): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { SearchMoviesPagingSource(api, query) }
        ).liveData
    }

    override fun getTopRatedMovie(): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { TopRatedMoviePagingSource(api) }
        ).liveData
    }

    override fun getMovieByGenre(genreId: Int): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { MovieByGenrePagingSource(api, genreId) }
        ).liveData
    }

    override suspend fun getGenres(): GenreResponse {
        return api.getGenres()
    }

    override suspend fun getVideos(movieId: Int): VideoResponse {
        return api.getVideos(movieId)
    }
}