package com.tmdb.android.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmdb.android.data.remote.api.MovieApi
import com.tmdb.android.data.remote.response.toDomain
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.utils.INITIAL_PAGE_INDEX

class MovieByGenrePagingSource(private val api: MovieApi, private val genreId: Int) :
    PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = api.getMovieByGenre(page, genreId).results

            LoadResult.Page(
                data = responseData.map { it.toDomain() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseData.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}