package com.example.mytmdbapp.presentation.movie.mappers

import com.example.domain.models.movie.Credits
import com.example.domain.models.movie.Details
import com.example.domain.models.movie.MovieBasicInfo
import com.example.mytmdbapp.presentation.movie.ui_model.CinemaItemUI
import com.example.mytmdbapp.presentation.movie.ui_model.CreditsUI
import com.example.mytmdbapp.presentation.movie.ui_model.MovieDetailsUI
import com.example.mytmdbapp.utils.toPercent
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() {

    fun toMovieDetailedUIModel(
        details: Details,
        credits: Credits,
        recommendations: MovieBasicInfo
    ): MovieDetailsUI {
        val creditsUI = toUICredits(credits)
        val recommendationsUI = toCinemaUIModel(recommendations)
        return MovieDetailsUI(
            details.id,
            details.title,
            details.releaseDate,
            details.voteAverage.toPercent(),
            details.posterPath,
            details.backdropPath,
            details.genres.map { it.name },
            details.overview,
            creditsUI,
            recommendationsUI
        )
    }

    private fun toUICredits(credits: Credits): List<CreditsUI> {
        return credits.cast.map {
            CreditsUI(it.id.toString(), it.name, it.character, it.profilePath)
        }
    }

    private fun toCinemaUIModel(movieBasicInfo: MovieBasicInfo): List<CinemaItemUI> {
        return movieBasicInfo.movieBasicInfoResult.map {
            CinemaItemUI(
                it.id,
                it.title,
                it.releaseDate,
                it.voteAverage.toPercent(),
                it.posterPath
            )
        }
    }
}
