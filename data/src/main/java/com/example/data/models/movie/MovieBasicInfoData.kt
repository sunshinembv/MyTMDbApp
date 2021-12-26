package com.example.data.models.movie

import com.example.domain.models.movie.MovieBasicInfo
import com.example.domain.models.movie.MovieBasicInfoResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieBasicInfoData(
    override val page: Int,
    @Json(name = "results")
    override val movieBasicInfoResult: List<MovieBasicInfoResultData>,
    @Json(name = "total_pages")
    override val totalPages: Int,
    @Json(name = "total_results")
    override val totalResults: Int
) : MovieBasicInfo

@JsonClass(generateAdapter = true)
data class MovieBasicInfoResultData(
    override val id: Int,
    @Json(name = "original_title")
    override val originalTitle: String,
    @Json(name = "release_date")
    override val releaseDate: String,
    @Json(name = "poster_path")
    override val posterPath: String?,
    override val title: String,
    @Json(name = "vote_average")
    override val voteAverage: Double
) : MovieBasicInfoResult
