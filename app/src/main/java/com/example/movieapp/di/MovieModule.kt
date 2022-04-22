package com.example.movieapp.di

import com.example.movieapp.common.utils.Dispatchers
import com.example.movieapp.feature_movies.data.remote_data.MovieService
import com.example.movieapp.feature_movies.data.repository.TopRatedRepositoryImpl
import com.example.movieapp.feature_movies.domain.repository.MoviesRepository
import com.example.movieapp.feature_movies.domain.use_cases.MoviesUseCase
import com.example.movieapp.feature_movies.domain.use_cases.PopularUseCase
import com.example.movieapp.feature_movies.domain.use_cases.TopRatedUseCase
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
    fun provideUseCases(repo: MoviesRepository): MoviesUseCase {
        return MoviesUseCase(TopRatedUseCase(repo), PopularUseCase(repo))
    }


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
    fun provideResponseHandler(): ResponseHandler = ResponseHandler.Base()

    @Provides
    @Singleton
    fun provideDispatchers(): Dispatchers = Dispatchers.Base()
}