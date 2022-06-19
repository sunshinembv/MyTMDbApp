package com.example.data.repository

import com.example.data.NetworkHandleService
import com.example.data.network.Api
import com.example.data.network.Keys
import com.example.domain.Result
import com.example.domain.models.movie.Credits
import com.example.domain.models.movie.Details
import com.example.domain.models.movie.MovieBasicInfo
import com.example.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: Api,
    private val networkHandleService: NetworkHandleService
) : MovieRepository {

    override suspend fun getPopularMovies(): MovieBasicInfo {
        return when (
            val result =
                networkHandleService.apiCall { api.getPopularMovies(Keys.apiKey()) }
        ) {
            is Result.Success -> result.data
            is Result.Error -> error(result.error)
            else -> error("Некорректное состояние")
        }
    }

    override suspend fun getTopRatedMovies(): MovieBasicInfo {
        return when (
            val result =
                networkHandleService.apiCall { api.getTopRatedMovies(Keys.apiKey()) }
        ) {
            is Result.Success -> result.data
            is Result.Error -> error(result.error)
            else -> error("Некорректное состояние")
        }
    }

    override suspend fun getUpcomingMovies(): MovieBasicInfo {
        return when (
            val result =
                networkHandleService.apiCall { api.getUpcomingMovies(Keys.apiKey()) }
        ) {
            is Result.Success -> result.data
            is Result.Error -> error(result.error)
            else -> error("Некорректное состояние")
        }
    }

    override suspend fun getDetailsByMovieId(id: Int): Details {
        return when (
            val result =
                networkHandleService.apiCall { api.getDetailsByMovieId(id, Keys.apiKey()) }
        ) {
            is Result.Success -> result.data
            is Result.Error -> error(result.error)
            else -> error("Некорректное состояние")
        }
    }

    override suspend fun getCreditsByMovieId(id: Int): Credits {
        return when (
            val result =
                networkHandleService.apiCall { api.getCreditsByMovieId(id, Keys.apiKey()) }
        ) {
            is Result.Success -> result.data
            is Result.Error -> error(result.error)
            else -> error("Некорректное состояние")
        }
    }

    override suspend fun getRecommendationsByMovieId(id: Int): MovieBasicInfo {
        return when (
            val result =
                networkHandleService.apiCall { api.getRecommendationsByMovieId(id, Keys.apiKey()) }
        ) {
            is Result.Success -> result.data
            is Result.Error -> error(result.error)
            else -> error("Некорректное состояние")
        }
    }
}
