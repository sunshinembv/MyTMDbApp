package com.example.mytmdbapp.di

import com.example.data.repository.MovieRepositoryImpl
import com.example.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun provideMovieRepositoryImpl(impl: MovieRepositoryImpl): MovieRepository
}