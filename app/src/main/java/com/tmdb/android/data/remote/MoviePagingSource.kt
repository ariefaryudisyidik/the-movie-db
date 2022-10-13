package com.tmdb.android.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmdb.android.data.remote.response.toDomain
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.utils.INITIAL_PAGE_INDEX
import javax.inject.Inject

class MoviePagingSource @Inject constructor(private val api: ApiService) :
    PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = api.getTopRatedMovie(page, params.loadSize).results

            LoadResult.Page(
                data = responseData.map { it.toDomain() },
                prevKey = if (page == INITIAL_PAGE_INDEX) null else page - 1,
                nextKey = if (responseData.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}