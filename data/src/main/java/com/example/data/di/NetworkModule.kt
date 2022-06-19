package com.example.data.di

import com.example.data.network.Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApi(): Api {
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val retrofit =
            Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create()).build()

        return retrofit.create()
    }

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
    }
}
