package com.tmdb.android.domain.usecase.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.tmdb.android.data.repository.MovieRepository
import com.tmdb.android.domain.model.Genre
import com.tmdb.android.utils.Resource
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): LiveData<Resource<List<Genre>>> = liveData {
        try {
            emit(Resource.Loading())
            val data = repository.getGenres().genres
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage!!))
        }
    }
}