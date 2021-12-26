package com.example.domain.usecases.movies

import com.example.domain.models.movie.Credits
import com.example.domain.repository.MovieRepository


class GetCreditsByMovieIdUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(id: Int): Credits {
        return movieRepository.getCreditsByMovieId(id)
    }
}