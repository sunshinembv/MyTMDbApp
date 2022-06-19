package com.example.mytmdbapp.presentation.movie.mappers

import com.example.domain.models.movie.MovieBasicInfoResult
import com.example.mytmdbapp.presentation.movie.ui_model.CinemaItemUI
import com.example.mytmdbapp.utils.toPercent
import javax.inject.Inject

class MovieBasicInfoMapper @Inject constructor() {

    fun toCinemaItemUI(movieBasicInfoResult: MovieBasicInfoResult): CinemaItemUI {
        return CinemaItemUI(
            movieBasicInfoResult.id,
            movieBasicInfoResult.title,
            movieBasicInfoResult.releaseDate,
            movieBasicInfoResult.voteAverage.toPercent(),
            movieBasicInfoResult.posterPath
        )
    }
}
