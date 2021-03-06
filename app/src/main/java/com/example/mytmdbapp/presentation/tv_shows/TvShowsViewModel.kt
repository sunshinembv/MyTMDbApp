package com.example.mytmdbapp.presentation.tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.repository.TvShowsRepositoryImpl
import com.example.domain.models.show.Show
import com.example.domain.usecases.tv_shows.GetPopularTvShowsListUseCase
import com.example.domain.usecases.tv_shows.GetTopRatingsTvShowsListUseCase
import com.example.domain.usecases.tv_shows.GetUpcomingTvShowsListUseCase
import com.example.mytmdbapp.presentation.movie.ui_model.CinemaItemUI

class TvShowsViewModel : ViewModel() {

    private val tvShowsRepositoryImpl = TvShowsRepositoryImpl()

    private val tvShowsPopularLiveData = MutableLiveData<List<CinemaItemUI>>()
    val popularTvShows: LiveData<List<CinemaItemUI>>
        get() = tvShowsPopularLiveData

    private val tvShowsTopRatingsLiveData = MutableLiveData<List<CinemaItemUI>>()
    val topRatingsTvShows: LiveData<List<CinemaItemUI>>
        get() = tvShowsTopRatingsLiveData

    private val tvShowsUpcomingLiveData = MutableLiveData<List<CinemaItemUI>>()
    val upcomingTvShows: LiveData<List<CinemaItemUI>>
        get() = tvShowsUpcomingLiveData

    fun getPopularMovies() {
        val tvShows = GetPopularTvShowsListUseCase(tvShowsRepositoryImpl).execute()
        val cinema = tvShows.map {
            tvShowsToCinemaUIModel(it)
        }
        tvShowsPopularLiveData.postValue(cinema)
    }

    fun getTopRatingsMovies() {
        val tvShows = GetTopRatingsTvShowsListUseCase(tvShowsRepositoryImpl).execute()
        val cinema = tvShows.map {
            tvShowsToCinemaUIModel(it)
        }
        tvShowsTopRatingsLiveData.postValue(cinema)
    }

    fun getUpcomingMovies() {
        val tvShows = GetUpcomingTvShowsListUseCase(tvShowsRepositoryImpl).execute()
        val cinema = tvShows.map {
            tvShowsToCinemaUIModel(it)
        }
        tvShowsUpcomingLiveData.postValue(cinema)
    }

    private fun tvShowsToCinemaUIModel(tvShows: Show): CinemaItemUI {
        return CinemaItemUI(1, tvShows.title, tvShows.date, 1, tvShows.posterPath)
    }
}
