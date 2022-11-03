package com.tmdb.android.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.tmdb.android.data.remote.response.VideoResponse
import com.tmdb.android.domain.usecase.movie.GetVideosUseCase
import com.tmdb.android.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase
) : ViewModel() {

    val getVideos = MutableLiveData<Resource<VideoResponse>>()
    fun setVideos(movieId: Int) = viewModelScope.launch {
        getVideosUseCase(movieId).asFlow().collect {
            getVideos.postValue(it)
        }
    }
}