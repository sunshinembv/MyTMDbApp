package com.example.mytmdbapp.presentation.movie.ui_model

import com.example.mytmdbapp.presentation.ui_model.CinemaUIModel

data class MovieDetailedUIModel(
    val id: Int,
    val title: String,
    val date: String,
    val rating: Int,
    val posterPath: String?,
    val bannerPath: String?,
    val genre: List<String>,
    val overView: String,
    val credits: List<CreditsUIModel>,
    val recommendations: List<CinemaUIModel>,
)