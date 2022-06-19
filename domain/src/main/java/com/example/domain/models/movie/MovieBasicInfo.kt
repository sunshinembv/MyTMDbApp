package com.example.domain.models.movie

interface MovieBasicInfo {
    val page: Int
    val movieBasicInfoResult: List<MovieBasicInfoResult>
    val totalPages: Int
    val totalResults: Int
}

interface MovieBasicInfoResult {
    val id: Int
    val originalTitle: String
    val releaseDate: String
    val posterPath: String?
    val title: String
    val voteAverage: Double
}
