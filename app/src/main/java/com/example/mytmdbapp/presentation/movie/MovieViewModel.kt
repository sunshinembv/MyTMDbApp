package com.example.mytmdbapp.presentation.movie

import androidx.lifecycle.*
import com.example.domain.usecases.movies.GetPopularMovieListUseCase
import com.example.domain.usecases.movies.GetTopRatingsMovieListUseCase
import com.example.domain.usecases.movies.GetUpcomingMovieListUseCase
import com.example.mytmdbapp.presentation.UIState
import com.example.mytmdbapp.presentation.movie.mappers.MovieBasicInfoMapper
import com.example.mytmdbapp.presentation.movie.ui_model.MovieDataUI
import com.example.mytmdbapp.presentation.movie.ui_model.CinemaItemUI
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class MovieViewModel(
    private val movieBasicInfoMapper: MovieBasicInfoMapper,
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val getTopRatingsMovieListUseCase: GetTopRatingsMovieListUseCase,
    private val getUpcomingMovieListUseCase: GetUpcomingMovieListUseCase,
) : ViewModel() {

    private val _movies = MutableStateFlow<UIState<MovieDataUI>>(UIState.IsLoading)
    val movies: StateFlow<UIState<MovieDataUI>> = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val popularMovies = getPopularMovies()
                val topRatingsMovies = getTopRatingsMovies()
                val upcomingMovies = getUpcomingMovies()
                val movieDataUi = MovieDataUI(popularMovies, topRatingsMovies, upcomingMovies)
                val uiState = UIState.Success(movieDataUi)
                _movies.emit(uiState)
            } catch (t: Throwable) {
                Timber.d(t)
                _movies.emit(UIState.Error(t.message.toString()))
            }
        }
    }

    private suspend fun getPopularMovies(): List<CinemaItemUI> {
        return getPopularMovieListUseCase.execute().movieBasicInfoResult.map {
            movieBasicInfoMapper.toCinemaItemUI(it)
        }
    }

    private suspend fun getTopRatingsMovies(): List<CinemaItemUI> {
        return getTopRatingsMovieListUseCase.execute().movieBasicInfoResult.map {
            movieBasicInfoMapper.toCinemaItemUI(it)
        }
    }

    private suspend fun getUpcomingMovies(): List<CinemaItemUI> {
        return getUpcomingMovieListUseCase.execute().movieBasicInfoResult.map {
            movieBasicInfoMapper.toCinemaItemUI(it)
        }
    }

    class Factory @Inject constructor(
        private val movieBasicInfoMapper: MovieBasicInfoMapper,
        private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
        private val getTopRatingsMovieListUseCase: GetTopRatingsMovieListUseCase,
        private val getUpcomingMovieListUseCase: GetUpcomingMovieListUseCase,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == MovieViewModel::class.java)
            return MovieViewModel(
                movieBasicInfoMapper,
                getPopularMovieListUseCase,
                getTopRatingsMovieListUseCase,
                getUpcomingMovieListUseCase
            ) as T
        }
    }

}