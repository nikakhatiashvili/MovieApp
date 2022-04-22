package com.example.movieapp.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import dagger.hilt.InstallIn
import javax.inject.Singleton
import com.squareup.moshi.Moshi
import dagger.hilt.components.SingletonComponent
import com.example.movieapp.common.utils.Dispatchers
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.example.movieapp.feature_movies.domain.utils.ResponseHandler
import com.example.movieapp.feature_movies.data.remote_data.movie_tv_shows.MovieService
import com.example.movieapp.feature_movies.data.remote_data.search.SearchService
import com.example.movieapp.feature_movies.domain.utils.Constants.BASE_URL
import com.example.movieapp.feature_movies.domain.repository.movie_repo.MoviesRepository
import com.example.movieapp.feature_movies.domain.use_cases.movie.movies.MoviesUseCase
import com.example.movieapp.feature_movies.data.repository.movie_repo.TopRatedRepositoryImpl
import com.example.movieapp.feature_movies.data.repository.search_repo.SearchRepositoryImpl
import com.example.movieapp.feature_movies.domain.repository.search_repo.SearchRepository
import com.example.movieapp.feature_movies.domain.use_cases.movie.popular.PopularUseCase
import com.example.movieapp.feature_movies.domain.use_cases.movie.upcoming.UpcomingUseCase
import com.example.movieapp.feature_movies.domain.use_cases.movie.top_rated.TopRatedUseCase
import com.example.movieapp.feature_movies.domain.use_cases.search.multi_search.MultiSearchUseCase
import com.example.movieapp.feature_movies.domain.use_cases.search.search.SearchUseCase


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
    fun provideRetrofit(): SearchService =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(SearchService::class.java)

    @Provides
    fun provideSearchUseCases(repo: SearchRepository): SearchUseCase {
        return SearchUseCase(
            searchUseCase = MultiSearchUseCase(repo),
        )
    }

    @Provides
    @Singleton
    fun provideSearchRepository(
        searchService: SearchService,
        responseHandler: ResponseHandler
    ): SearchRepository {
        return SearchRepositoryImpl(searchService, responseHandler)
    }

    @Provides
    fun provideUseCases(repo: MoviesRepository): MoviesUseCase {
        return MoviesUseCase(
            topRatedUseCase = TopRatedUseCase(repo),
            popularUseCase = PopularUseCase(repo),
            upcomingUseCase = UpcomingUseCase(repo)
        )
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieService: MovieService,
        responseHandler: ResponseHandler
    ): MoviesRepository {
        return TopRatedRepositoryImpl(movieService, responseHandler)
    }

    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler = ResponseHandler.Base()

    @Provides
    @Singleton
    fun provideDispatchers(): Dispatchers = Dispatchers.Base()
}