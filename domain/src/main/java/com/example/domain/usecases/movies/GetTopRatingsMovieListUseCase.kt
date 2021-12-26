package com.example.domain.usecases.movies

import com.example.domain.models.movie.MovieBasicInfo
import com.example.domain.repository.MovieRepository

class GetTopRatingsMovieListUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(): MovieBasicInfo {
        return movieRepository.getTopRatingsMovies()
    }
}