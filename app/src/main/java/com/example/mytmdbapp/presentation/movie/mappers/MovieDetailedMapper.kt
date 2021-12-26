package com.example.mytmdbapp.presentation.movie.mappers

import com.example.domain.models.movie.Credits
import com.example.domain.models.movie.Details
import com.example.domain.models.movie.MovieBasicInfo
import com.example.mytmdbapp.presentation.movie.ui_model.CreditsUIModel
import com.example.mytmdbapp.presentation.movie.ui_model.MovieDetailedUIModel
import com.example.mytmdbapp.presentation.ui_model.CinemaUIModel
import javax.inject.Inject

class MovieDetailedMapper @Inject constructor() {

    fun toMovieDetailedUIModel(
        details: Details,
        credits: Credits,
        recommendations: MovieBasicInfo
    ): MovieDetailedUIModel {
        val creditsUI = toUICredits(credits)
        val recommendationsUI = toCinemaUIModel(recommendations)
        return MovieDetailedUIModel(
            details.id,
            details.title,
            details.releaseDate,
            (details.voteAverage * 10).toInt(),
            details.posterPath,
            details.backdropPath,
            details.genres.map { it.name },
            details.overview,
            creditsUI,
            recommendationsUI
        )
    }

    private fun toUICredits(credits: Credits): List<CreditsUIModel> {
        return credits.cast.map {
            CreditsUIModel(it.id.toString(), it.name, it.character, it.profilePath)
        }
    }

    private fun toCinemaUIModel(movieBasicInfo: MovieBasicInfo): List<CinemaUIModel> {
        return movieBasicInfo.movieBasicInfoResult.map {
            CinemaUIModel(
                it.id,
                it.title,
                it.releaseDate,
                (it.voteAverage * 10).toInt(),
                it.posterPath
            )
        }
    }
}