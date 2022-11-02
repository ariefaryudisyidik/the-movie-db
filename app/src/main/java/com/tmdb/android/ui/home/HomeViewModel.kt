package com.tmdb.android.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.domain.usecase.movie.GetGenresUseCase
import com.tmdb.android.domain.usecase.movie.GetMovieByGenreUseCase
import com.tmdb.android.domain.usecase.movie.GetTopRatedMovieUseCase
import com.tmdb.android.domain.usecase.movie.SearchMoviesUseCase
import com.tmdb.android.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getMovieByGenreUseCase: GetMovieByGenreUseCase,
    getGenresUseCase: GetGenresUseCase,
    getTopRatedMovieUseCase: GetTopRatedMovieUseCase,
) : ViewModel() {

    val searchMoviesResult = MutableLiveData<PagingData<Movie>>()
    fun searchMovies(query: String) {
        viewModelScope.launch {
            searchMoviesUseCase(query).cachedIn(viewModelScope).asFlow().collect {
                searchMoviesResult.postValue(it)
            }
        }
    }

    val getGenreId = MutableLiveData<Int>()
    val getMovieByGenreResult = MutableLiveData<PagingData<Movie>>()
    fun getMovieByGenre(genreId: Int) {
        getGenreId.postValue(genreId)
        viewModelScope.launch {
            getMovieByGenreUseCase(genreId).cachedIn(viewModelScope).asFlow().collect {
                getMovieByGenreResult.postValue(it)
            }
        }
    }


    val getGenres = getGenresUseCase()
    val getTopRatedMovie = getTopRatedMovieUseCase().cachedIn(viewModelScope)

    val navigateToDetail = MutableLiveData<Event<Movie>>()
    fun onMovieClicked(movie: Movie) {
        navigateToDetail.postValue(Event(movie))
    }
}