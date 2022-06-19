package com.example.mytmdbapp.presentation.movie.ui_model

data class MovieDataUI(
    val moviesPopular: List<CinemaItemUI>,
    val moviesTopRated: List<CinemaItemUI>,
    val moviesUpcoming: List<CinemaItemUI>
)
