package com.tmdb.android.domain.usecase.movie

import com.tmdb.android.domain.model.Genre
import com.tmdb.android.domain.repository.MovieRepository
import com.tmdb.android.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Genre>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getGenres().genres
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage!!))
        }
    }
}