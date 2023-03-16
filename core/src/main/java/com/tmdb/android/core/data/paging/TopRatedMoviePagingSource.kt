package com.tmdb.android.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmdb.android.core.data.remote.api.MovieApi
import com.tmdb.android.core.data.remote.response.toDomain
import com.tmdb.android.core.domain.model.Movie
import com.tmdb.android.core.utils.INITIAL_PAGE_INDEX

class TopRatedMoviePagingSource(private val api: MovieApi) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = api.getTopRatedMovie(page).results

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