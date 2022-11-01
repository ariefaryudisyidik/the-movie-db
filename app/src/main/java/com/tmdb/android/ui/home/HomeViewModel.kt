package com.tmdb.android.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.domain.usecase.movie.GetGenresUseCase
import com.tmdb.android.domain.usecase.movie.GetTopRatedMovieUseCase
import com.tmdb.android.domain.usecase.movie.SearchMoviesUseCase
import com.tmdb.android.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getGenresUseCase: GetGenresUseCase,
    getTopRatedMovieUseCase: GetTopRatedMovieUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
) : ViewModel() {

    val genres = getGenresUseCase()

    val topRatedMovies = getTopRatedMovieUseCase().cachedIn(viewModelScope)

    val movies = MutableLiveData<PagingData<Movie>>()
    fun searchMovies(query: String) {
        viewModelScope.launch {
            searchMoviesUseCase(query).cachedIn(viewModelScope).asFlow().collect {
                movies.postValue(it)
            }
        }
    }

    val navigateToDetail = MutableLiveData<Event<Movie>>()
    fun onMovieClicked(movie: Movie) {
        navigateToDetail.postValue(Event(movie))
    }
}