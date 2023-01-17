package com.tmdb.android.domain.usecase.movie

import com.tmdb.android.data.remote.response.toDomain
import com.tmdb.android.domain.model.Review
import com.tmdb.android.domain.repository.MovieRepository
import com.tmdb.android.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetReviewsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<List<Review>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getReviews(movieId).results.map { it.toDomain() }
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage!!))
        }
    }
}