package com.example.mytmdbapp.di

import com.example.domain.repository.MovieRepository
import com.example.domain.usecases.movies.GetCreditsByMovieIdUseCase
import com.example.domain.usecases.movies.GetDetailsByMovieIdUseCase
import com.example.domain.usecases.movies.GetPopularMovieListUseCase
import com.example.domain.usecases.movies.GetRecommendationsByMovieIdUseCase
import com.example.domain.usecases.movies.GetTopRatedMovieListUseCase
import com.example.domain.usecases.movies.GetUpcomingMovieListUseCase
import dagger.Module
import dagger.Provides

@Module
class MoviesUseCaseModule {

    @Provides
    fun provideGetCreditsByMovieIdUseCase(movieRepository: MovieRepository):
        GetCreditsByMovieIdUseCase {

        return GetCreditsByMovieIdUseCase(movieRepository)
    }

    @Provides
    fun provideGetDetailsByMovieIdUseCase(movieRepository: MovieRepository):
        GetDetailsByMovieIdUseCase {

        return GetDetailsByMovieIdUseCase(movieRepository)
    }

    @Provides
    fun provideGetPopularMovieListUseCase(movieRepository: MovieRepository):
        GetPopularMovieListUseCase {

        return GetPopularMovieListUseCase(movieRepository)
    }

    @Provides
    fun provideGetRecommendationsByMovieIdUseCase(movieRepository: MovieRepository):
        GetRecommendationsByMovieIdUseCase {

        return GetRecommendationsByMovieIdUseCase(movieRepository)
    }

    @Provides
    fun provideGetTopRatedMovieListUseCase(movieRepository: MovieRepository):
        GetTopRatedMovieListUseCase {

        return GetTopRatedMovieListUseCase(movieRepository)
    }

    @Provides
    fun provideGetUpcomingMovieListUseCase(movieRepository: MovieRepository):
        GetUpcomingMovieListUseCase {

        return GetUpcomingMovieListUseCase(movieRepository)
    }
}
