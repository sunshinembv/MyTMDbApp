package com.example.data.network

import com.example.data.models.movie.CreditsData
import com.example.data.models.movie.DetailsData
import com.example.data.models.movie.MovieBasicInfoData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("3/movie/{movie_id}")
    suspend fun getDetailsByMovieId(
        @Path("movie_id") movieId: Int
    ): Response<DetailsData>

    @GET("3/movie/{movie_id}/credits")
    suspend fun getCreditsByMovieId(
        @Path("movie_id") movieId: Int
    ): Response<CreditsData>

    @GET("3/movie/{movie_id}/recommendations")
    suspend fun getRecommendationsByMovieId(
        @Path("movie_id") movieId: Int
    ): Response<MovieBasicInfoData>

    @GET("3/movie/popular")
    suspend fun getPopularMovies(): Response<MovieBasicInfoData>

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(): Response<MovieBasicInfoData>

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MovieBasicInfoData>
}
