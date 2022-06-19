package com.example.mytmdbapp.presentation.movie.ui_model

data class MovieDetailsUI(
    val id: Int,
    val title: String,
    val date: String,
    val rating: Int,
    val posterPath: String?,
    val bannerPath: String?,
    val genre: List<String>,
    val overView: String,
    val credits: List<CreditsUI>,
    val recommendations: List<CinemaItemUI>,
)
