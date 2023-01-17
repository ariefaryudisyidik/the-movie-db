package com.tmdb.android.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.domain.usecase.movie.SearchMoviesUseCase
import com.tmdb.android.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    val getQuery = MutableLiveData<Boolean>()
    fun setQuery(isEmpty: Boolean) {
        getQuery.postValue(isEmpty)
    }

    val getSearchMovies = MutableLiveData<PagingData<Movie>>()
    fun setSearchMovies(query: String) =
        searchMoviesUseCase(query).cachedIn(viewModelScope).onEach {
            getSearchMovies.postValue(it)
        }.launchIn(viewModelScope)

    val navigateToDetail = MutableLiveData<Event<Movie>>()
    fun onMovieClicked(movie: Movie) {
        navigateToDetail.postValue(Event(movie))
    }
}