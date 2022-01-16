package com.example.mytmdbapp.presentation.movie.ui_model

data class MovieDataUI(
    val moviesPopular: List<CinemaItemUI>,
    val moviesTopRatings: List<CinemaItemUI>,
    val moviesUpcoming: List<CinemaItemUI>
)
