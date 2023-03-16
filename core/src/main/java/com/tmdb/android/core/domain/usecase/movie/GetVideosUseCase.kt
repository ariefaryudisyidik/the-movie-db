package com.tmdb.android.core.domain.usecase.movie

import com.tmdb.android.core.data.remote.response.VideoResponse
import com.tmdb.android.core.domain.repository.MovieRepository
import com.tmdb.android.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<VideoResponse>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getVideos(movieId)
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage!!))
        }
    }
}