package com.example.data.models.movie

import com.example.domain.models.movie.Cast
import com.example.domain.models.movie.Credits
import com.example.domain.models.movie.Crew
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditsData(
    override val id: Int,
    override val cast: List<CastData>,
    override val crew: List<CrewData>
) : Credits

@JsonClass(generateAdapter = true)
data class CastData(
    override val id: Int,
    override val name: String,
    @Json(name = "original_name")
    override val originalName: String,
    @Json(name = "profile_path")
    override val profilePath: String?,
    @Json(name = "cast_id")
    override val castID: Long,
    override val character: String,
    @Json(name = "credit_id")
    override val creditID: String
) : Cast

@JsonClass(generateAdapter = true)
data class CrewData(
    override val id: Int,
    override val name: String,
    @Json(name = "original_name")
    override val originalName: String,
    @Json(name = "profile_path")
    override val profilePath: String?,
    @Json(name = "credit_id")
    override val creditID: String,
    override val job: String
) : Crew
