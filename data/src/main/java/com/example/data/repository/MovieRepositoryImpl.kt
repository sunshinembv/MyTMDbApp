package com.example.data.repository

import com.example.data.NetworkHandleService
import com.example.data.network.Api
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
        return networkHandleService.apiCall { api.getPopularMovies() }
    }

    override suspend fun getTopRatedMovies(): MovieBasicInfo {
        return networkHandleService.apiCall { api.getTopRatedMovies() }
    }

    override suspend fun getUpcomingMovies(): MovieBasicInfo {
        return networkHandleService.apiCall { api.getUpcomingMovies() }
    }

    override suspend fun getDetailsByMovieId(id: Int): Details {
        return networkHandleService.apiCall { api.getDetailsByMovieId(id) }
    }

    override suspend fun getCreditsByMovieId(id: Int): Credits {
        return networkHandleService.apiCall { api.getCreditsByMovieId(id) }
    }

    override suspend fun getRecommendationsByMovieId(id: Int): MovieBasicInfo {
        return networkHandleService.apiCall { api.getRecommendationsByMovieId(id) }
    }
}
