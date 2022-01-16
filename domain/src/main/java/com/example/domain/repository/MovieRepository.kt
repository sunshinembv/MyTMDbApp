package com.example.domain.repository

import com.example.domain.models.movie.Credits
import com.example.domain.models.movie.Details
import com.example.domain.models.movie.MovieBasicInfo

interface MovieRepository {
    suspend fun getPopularMovies(): MovieBasicInfo
    suspend fun getTopRatingsMovies(): MovieBasicInfo
    suspend fun getUpcomingMovies(): MovieBasicInfo
    suspend fun getDetailsByMovieId(id: Int): Details
    suspend fun getCreditsByMovieId(id: Int): Credits
    suspend fun getRecommendationsByMovieId(id: Int): MovieBasicInfo
}
