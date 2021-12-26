package com.example.mytmdbapp.presentation.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.movies.GetPopularMovieListUseCase
import com.example.domain.usecases.movies.GetTopRatingsMovieListUseCase
import com.example.domain.usecases.movies.GetUpcomingMovieListUseCase
import com.example.mytmdbapp.presentation.movie.mappers.MovieBasicInfoMapper
import com.example.mytmdbapp.presentation.ui_model.CinemaUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieBasicInfoMapper: MovieBasicInfoMapper,
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val getTopRatingsMovieListUseCase: GetTopRatingsMovieListUseCase,
    private val getUpcomingMovieListUseCase: GetUpcomingMovieListUseCase,
) : ViewModel() {

    private val moviesPopularLiveData = MutableLiveData<List<CinemaUIModel>>()
    val popularMovies: LiveData<List<CinemaUIModel>>
        get() = moviesPopularLiveData

    private val moviesTopRatingsLiveData = MutableLiveData<List<CinemaUIModel>>()
    val topRatingsMovies: LiveData<List<CinemaUIModel>>
        get() = moviesTopRatingsLiveData

    private val moviesUpcomingLiveData = MutableLiveData<List<CinemaUIModel>>()
    val upcomingMovies: LiveData<List<CinemaUIModel>>
        get() = moviesUpcomingLiveData

    private val loadLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = loadLiveData

    private suspend fun getPopularMovies(): List<CinemaUIModel> {
        val moviesBasicInfo = getPopularMovieListUseCase.execute()
        return moviesBasicInfo.movieBasicInfoResult.map {
            movieBasicInfoMapper.toCinemaUIModel(it)
        }
    }

    private suspend fun getTopRatingsMovies(): List<CinemaUIModel> {
        val moviesBasicInfo = getTopRatingsMovieListUseCase.execute()
        return moviesBasicInfo.movieBasicInfoResult.map {
            movieBasicInfoMapper.toCinemaUIModel(it)
        }
    }

    private suspend fun getUpcomingMovies(): List<CinemaUIModel> {
        val moviesBasicInfo = getUpcomingMovieListUseCase.execute()
        return moviesBasicInfo.movieBasicInfoResult.map {
            movieBasicInfoMapper.toCinemaUIModel(it)
        }
    }

    fun getMovies() {
        viewModelScope.launch {
            try {
                loadLiveData.postValue(true)
                val deferredPopularMovies = async {
                    getPopularMovies()
                }
                val deferredTopRatingsMovies = async {
                    getTopRatingsMovies()
                }
                val deferredUpcomingMovies = async {
                    getUpcomingMovies()
                }
                val popularMovies = deferredPopularMovies.await()
                val topRatingsMovies = deferredTopRatingsMovies.await()
                val upcomingMovies = deferredUpcomingMovies.await()
                moviesPopularLiveData.postValue(popularMovies)
                moviesTopRatingsLiveData.postValue(topRatingsMovies)
                moviesUpcomingLiveData.postValue(upcomingMovies)
            } catch (t: Throwable) {
                //TODO Доделать
                Log.d("Error", t.toString())
            } finally {
                loadLiveData.postValue(false)
            }
        }
    }

}