package com.example.domain.usecases.movies

import com.example.domain.models.movie.MovieBasicInfo
import com.example.domain.repository.MovieRepository

class GetRecommendationsByMovieIdUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(id: Int): MovieBasicInfo {
        return movieRepository.getRecommendationsByMovieId(id)
    }
}
