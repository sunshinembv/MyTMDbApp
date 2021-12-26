package com.example.domain.repository

import com.example.domain.models.show.Show


interface TvShowsRepository {
    fun getPopularTvShows(): List<Show>
    fun getTopRatingsTvShows(): List<Show>
    fun getUpcomingTvShows(): List<Show>
}