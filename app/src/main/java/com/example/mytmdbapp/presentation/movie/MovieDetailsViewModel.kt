package com.example.mytmdbapp.presentation.movie

import androidx.lifecycle.*
import com.example.domain.usecases.movies.*
import com.example.mytmdbapp.presentation.UIState
import com.example.mytmdbapp.presentation.movie.mappers.MovieDetailsMapper
import com.example.mytmdbapp.presentation.movie.ui_model.MovieDetailsUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsViewModel(
    private val movieDetailsMapper: MovieDetailsMapper,
    private val getDetailsByMovieIdUseCase: GetDetailsByMovieIdUseCase,
    private val getCreditsByMovieIdUseCase: GetCreditsByMovieIdUseCase,
    private val getRecommendationsByMovieIdUseCase: GetRecommendationsByMovieIdUseCase,
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<UIState<MovieDetailsUI>>(UIState.IsLoading)
    val movieDetails: StateFlow<UIState<MovieDetailsUI>> = _movieDetails.asStateFlow()

    fun getMovieDetailedById(id: Int) {
        viewModelScope.launch {
            try {
                val details = getDetailsByMovieIdUseCase.execute(id)
                val credits = getCreditsByMovieIdUseCase.execute(id)
                val recommendations = getRecommendationsByMovieIdUseCase.execute(id)
                val movieUIModel =
                    movieDetailsMapper.toMovieDetailedUIModel(details, credits, recommendations)
                _movieDetails.emit(UIState.Success(movieUIModel))
            } catch (t: Throwable) {
                Timber.d(t)
                _movieDetails.emit(UIState.Error(t.message.toString()))
            }
        }
    }

    class Factory @Inject constructor(
        private val movieDetailsMapper: MovieDetailsMapper,
        private val getDetailsByMovieIdUseCase: GetDetailsByMovieIdUseCase,
        private val getCreditsByMovieIdUseCase: GetCreditsByMovieIdUseCase,
        private val getRecommendationsByMovieIdUseCase: GetRecommendationsByMovieIdUseCase,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == MovieDetailsViewModel::class.java)
            return MovieDetailsViewModel(
                movieDetailsMapper,
                getDetailsByMovieIdUseCase,
                getCreditsByMovieIdUseCase,
                getRecommendationsByMovieIdUseCase
            ) as T
        }
    }
}