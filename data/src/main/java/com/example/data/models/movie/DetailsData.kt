package com.example.data.models.movie

import com.example.domain.models.movie.Details
import com.example.domain.models.movie.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailsData(
    @Json(name = "backdrop_path")
    override val backdropPath: String,
    override val genres: List<GenreData>,
    override val id: Int,
    @Json(name = "imdb_id")
    override val imdbID: String,
    @Json(name = "original_title")
    override val originalTitle: String,
    override val overview: String,
    @Json(name = "poster_path")
    override val posterPath: String?,
    @Json(name = "release_date")
    override val releaseDate: String,
    override val title: String,
    @Json(name = "vote_average")
    override val voteAverage: Double
) : Details

@JsonClass(generateAdapter = true)
data class GenreData(
    override val id: Int,
    override val name: String
) : Genre
