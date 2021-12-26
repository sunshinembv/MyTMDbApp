package com.example.domain.usecases.tv_shows

import com.example.domain.models.show.Show
import com.example.domain.repository.TvShowsRepository

class GetPopularTvShowsListUseCase(private val tvShowsRepository: TvShowsRepository) {

    fun execute(): List<Show> {
        return tvShowsRepository.getPopularTvShows()
    }
}