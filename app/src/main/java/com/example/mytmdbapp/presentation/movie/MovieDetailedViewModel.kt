package com.example.mytmdbapp.presentation.movie

import android.util.Log
import androidx.lifecycle.*
import com.example.domain.usecases.movies.*
import com.example.mytmdbapp.presentation.movie.mappers.MovieDetailedMapper
import com.example.mytmdbapp.presentation.movie.ui_model.MovieDetailedUIModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailedViewModel(
    private val movieDetailedMapper: MovieDetailedMapper,
    private val getDetailsByMovieIdUseCase: GetDetailsByMovieIdUseCase,
    private val getCreditsByMovieIdUseCase: GetCreditsByMovieIdUseCase,
    private val getRecommendationsByMovieIdUseCase: GetRecommendationsByMovieIdUseCase,
) : ViewModel() {

    private val movieLiveData = MutableLiveData<MovieDetailedUIModel>()
    val movie: LiveData<MovieDetailedUIModel>
        get() = movieLiveData

    private val loadLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = loadLiveData

    fun getMovieDetailedById(id: Int) {
        viewModelScope.launch {
            try {
                loadLiveData.postValue(true)
                val deferredDetails = async {
                    getDetailsByMovieIdUseCase.execute(id)
                }
                val deferredCredits = async {
                    getCreditsByMovieIdUseCase.execute(id)
                }
                val deferredRecommendations = async {
                    getRecommendationsByMovieIdUseCase.execute(id)
                }
                val details = deferredDetails.await()
                val credits = deferredCredits.await()
                val recommendations = deferredRecommendations.await()
                val movieUIModel =
                    movieDetailedMapper.toMovieDetailedUIModel(details, credits, recommendations)
                movieLiveData.postValue(movieUIModel)
            } catch (t: Throwable) {
                //TODO Доделать
                Log.d("Error", "ERROR UI $t")
            } finally {
                loadLiveData.postValue(false)
            }
        }
    }

    class Factory @Inject constructor(
        private val movieDetailedMapper: MovieDetailedMapper,
        private val getDetailsByMovieIdUseCase: GetDetailsByMovieIdUseCase,
        private val getCreditsByMovieIdUseCase: GetCreditsByMovieIdUseCase,
        private val getRecommendationsByMovieIdUseCase: GetRecommendationsByMovieIdUseCase,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == MovieDetailedViewModel::class.java)
            return MovieDetailedViewModel(
                movieDetailedMapper,
                getDetailsByMovieIdUseCase,
                getCreditsByMovieIdUseCase,
                getRecommendationsByMovieIdUseCase
            ) as T
        }
    }
}