package com.example.mytmdbapp.presentation.movie.mappers

import com.example.domain.models.movie.MovieBasicInfoResult
import com.example.mytmdbapp.presentation.ui_model.CinemaUIModel
import javax.inject.Inject

class MovieBasicInfoMapper @Inject constructor() {

    fun toCinemaUIModel(movieBasicInfoResult: MovieBasicInfoResult): CinemaUIModel {
        return CinemaUIModel(
            movieBasicInfoResult.id,
            movieBasicInfoResult.title,
            movieBasicInfoResult.releaseDate,
            (movieBasicInfoResult.voteAverage * 10).toInt(),
            movieBasicInfoResult.posterPath
        )
    }
}