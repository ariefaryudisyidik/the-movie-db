package com.tmdb.android.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.tmdb.android.data.remote.response.VideoResponse
import com.tmdb.android.domain.model.Review
import com.tmdb.android.domain.usecase.movie.GetReviewsUseCase
import com.tmdb.android.domain.usecase.movie.GetVideosUseCase
import com.tmdb.android.utils.Event
import com.tmdb.android.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase,
    private val getReviewsUseCase: GetReviewsUseCase
) : ViewModel() {

    val getVideos = MutableLiveData<Event<Resource<VideoResponse>>>()
    fun setVideos(movieId: Int) = viewModelScope.launch {
        getVideosUseCase(movieId).asFlow().collect {
            getVideos.postValue(Event(it))
        }
    }

    val getReviews = MutableLiveData<Resource<List<Review>>>()
    fun setReviews(movieId: Int) = viewModelScope.launch {
        getReviewsUseCase(movieId).asFlow().collect {
            getReviews.postValue(it)
        }
    }
}