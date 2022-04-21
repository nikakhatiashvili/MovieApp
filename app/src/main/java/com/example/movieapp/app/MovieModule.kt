package com.example.movieapp.app

import com.example.movieapp.feature_movies.data.remote_data.MovieService
import com.example.movieapp.feature_movies.data.repository.TopRatedRepositoryImpl
import com.example.movieapp.feature_movies.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MovieModule {


    @Provides
    @Singleton
    fun provideMovieRepository(
        authService: MovieService,
    ): MoviesRepository {
        return TopRatedRepositoryImpl(authService)
    }

}