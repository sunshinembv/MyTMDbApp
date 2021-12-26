package com.example.mytmdbapp.di

import com.example.data.repository.MovieRepositoryImpl
import com.example.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    @ViewModelScoped
    fun provideMovieRepositoryImpl(impl: MovieRepositoryImpl): MovieRepository {
        return impl
    }
}