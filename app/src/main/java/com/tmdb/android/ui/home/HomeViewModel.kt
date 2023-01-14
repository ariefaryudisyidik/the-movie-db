package com.tmdb.android.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tmdb.android.domain.model.Genre
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.domain.usecase.movie.GetGenresUseCase
import com.tmdb.android.domain.usecase.movie.GetMovieByGenreUseCase
import com.tmdb.android.domain.usecase.movie.GetTopRatedMovieUseCase
import com.tmdb.android.utils.Event
import com.tmdb.android.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val getTopRatedMovieUseCase: GetTopRatedMovieUseCase,
    private val getMovieByGenreUseCase: GetMovieByGenreUseCase,
) : ViewModel() {

    val getGenres = MutableLiveData<Resource<List<Genre>>>()
    fun setGenres() = getGenresUseCase.invoke().onEach {
        getGenres.postValue(it)
    }.launchIn(viewModelScope)

//    val getTopRatedMovie = MutableLiveData<PagingData<Movie>>()
//    fun setTopRatedMovie() = viewModelScope.launch {
//        getTopRatedMovieUseCase().cachedIn(viewModelScope).asFlow().collect {
//            getTopRatedMovie.postValue(it)
//        }
//    }

    val getGenreId = MutableLiveData<Int>()
    val getMovieByGenre = MutableLiveData<PagingData<Movie>>()
    fun setMovieByGenre(genreId: Int) {
        getGenreId.postValue(genreId)
        getMovieByGenreUseCase.invoke(genreId).cachedIn(viewModelScope).onEach {
            getMovieByGenre.postValue(it)
        }.launchIn(viewModelScope)
    }

    val navigateToDetail = MutableLiveData<Event<Movie>>()
    fun onMovieClicked(movie: Movie) {
        navigateToDetail.postValue(Event(movie))
    }
}