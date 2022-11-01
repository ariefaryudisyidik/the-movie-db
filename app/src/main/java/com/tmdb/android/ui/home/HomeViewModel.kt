package com.tmdb.android.ui.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.domain.usecase.movie.GetTopRatedMovieUseCase
import com.tmdb.android.domain.usecase.movie.SearchMoviesUseCase
import com.tmdb.android.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getTopRatedMovieUseCase: GetTopRatedMovieUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    val topRatedMovies: LiveData<PagingData<Movie>> =
        getTopRatedMovieUseCase().cachedIn(viewModelScope)

    val movies = MutableLiveData<PagingData<Movie>>()

    fun searchMovies(query: String) {
        viewModelScope.launch {
            searchMoviesUseCase(query).cachedIn(viewModelScope).asFlow().collect {
                movies.postValue(it)
            }
        }
    }

    private val _navigateToDetail = MutableLiveData<Event<Movie>>()
    val navigateToDetail: LiveData<Event<Movie>> get() = _navigateToDetail

    fun onMovieClicked(movie: Movie) {
        _navigateToDetail.value = Event(movie)
    }
}