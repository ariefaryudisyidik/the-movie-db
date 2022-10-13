package com.tmdb.android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.domain.usecase.movie.GetTopRatedMovieUseCase
import com.tmdb.android.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getTopRatedMovieUseCase: GetTopRatedMovieUseCase,
) : ViewModel() {

    private val _topRatedMovies = getTopRatedMovieUseCase().cachedIn(viewModelScope)
    val topRatedMovies: LiveData<PagingData<Movie>> get() = _topRatedMovies

    private val _navigateToDetail = MutableLiveData<Event<Movie>>()
    val navigateToDetail: LiveData<Event<Movie>> get() = _navigateToDetail

    fun onMovieClicked(movie: Movie) {
        _navigateToDetail.value = Event(movie)
    }
}