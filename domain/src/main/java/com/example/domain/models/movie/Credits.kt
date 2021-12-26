package com.example.domain.models.movie

interface Credits {
    val id: Int
    val cast: List<Cast>
    val crew: List<Crew>
}

interface Cast {
    val id: Int
    val name: String
    val originalName: String
    val profilePath: String?
    val castID: Long
    val character: String
    val creditID: String
}

interface Crew {
    val id: Int
    val name: String
    val originalName: String
    val profilePath: String?
    val creditID: String
    val job: String
}
