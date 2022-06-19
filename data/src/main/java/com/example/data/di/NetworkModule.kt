package com.example.data.di

import com.example.data.network.Api
import com.example.data.network.Keys
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
            .addNetworkInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            ).addNetworkInterceptor { chain ->
                val originalRequest = chain.request()
                val modifiedUrl = originalRequest.url
                    .newBuilder()
                    .addQueryParameter(QUERY_PARAMETER_API_KEY, Keys.apiKey())
                    .build()
                val modifiedRequest = originalRequest.newBuilder().url(modifiedUrl).build()
                chain.proceed(modifiedRequest)
            }
            .build()

        val retrofit =
            Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create()).build()

        return retrofit.create()
    }

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/"
        private const val QUERY_PARAMETER_API_KEY = "api_key"
    }
}
