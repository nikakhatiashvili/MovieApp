package com.example.movieapp.di

import com.example.movieapp.feature_movies.data.remote_data.MovieService
import com.example.movieapp.feature_movies.data.repository.TopRatedRepositoryImpl
import com.example.movieapp.feature_movies.domain.repository.MoviesRepository
import com.example.movieapp.feature_movies.domain.utils.Constants.BASE_URL
import com.example.movieapp.feature_movies.domain.utils.ResponseHandler
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MovieModule {


    @Provides
    @Singleton
    fun provideRetrofitCurrency(): MovieService =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(MovieService::class.java)

    @Provides
    @Singleton
    fun provideMovieRepository(
        authService: MovieService,
        responseHandler: ResponseHandler
    ): MoviesRepository {
        return TopRatedRepositoryImpl(authService, responseHandler)
    }

    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler.Base()
    }
}