package com.example.domain.usecases.movies

import com.example.domain.models.movie.Details
import com.example.domain.repository.MovieRepository

class GetDetailsByMovieIdUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(id: Int): Details {
        return movieRepository.getDetailsByMovieId(id)
    }
}