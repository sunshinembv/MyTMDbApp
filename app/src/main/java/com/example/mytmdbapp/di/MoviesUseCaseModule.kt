package com.example.mytmdbapp.di

import com.example.domain.repository.MovieRepository
import com.example.domain.usecases.movies.*
import dagger.Module
import dagger.Provides

@Module
class MoviesUseCaseModule {

    @Provides
    fun provideGetCreditsByMovieIdUseCase(movieRepository: MovieRepository): GetCreditsByMovieIdUseCase {
        return GetCreditsByMovieIdUseCase(movieRepository)
    }

    @Provides
    fun provideGetDetailsByMovieIdUseCase(movieRepository: MovieRepository): GetDetailsByMovieIdUseCase {
        return GetDetailsByMovieIdUseCase(movieRepository)
    }

    @Provides
    fun provideGetPopularMovieListUseCase(movieRepository: MovieRepository): GetPopularMovieListUseCase {
        return GetPopularMovieListUseCase(movieRepository)
    }

    @Provides
    fun provideGetRecommendationsByMovieIdUseCase(movieRepository: MovieRepository): GetRecommendationsByMovieIdUseCase {
        return GetRecommendationsByMovieIdUseCase(movieRepository)
    }

    @Provides
    fun provideGetTopRatingsMovieListUseCase(movieRepository: MovieRepository): GetTopRatingsMovieListUseCase {
        return GetTopRatingsMovieListUseCase(movieRepository)
    }

    @Provides
    fun provideGetUpcomingMovieListUseCase(movieRepository: MovieRepository): GetUpcomingMovieListUseCase {
        return GetUpcomingMovieListUseCase(movieRepository)
    }
}