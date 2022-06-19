package com.example.mytmdbapp.di

import com.example.data.di.NetworkModule
import com.example.mytmdbapp.presentation.movie.MovieDetailsFragment
import com.example.mytmdbapp.presentation.movie.MovieFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [MoviesUseCaseModule::class, RepositoryModule::class, NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(movieFragment: MovieFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)
}
