package com.example.domain.models.movie

interface Details {
    val backdropPath: String
    val genres: List<Genre>
    val id: Int
    val imdbID: String
    val originalTitle: String
    val overview: String
    val posterPath: String?
    val releaseDate: String
    val title: String
    val voteAverage: Double
}

interface Genre {
    val id: Int
    val name: String
}

