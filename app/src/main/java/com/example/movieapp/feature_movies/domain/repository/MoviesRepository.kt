package com.example.movieapp.feature_movies.domain.repository

import com.example.movieapp.feature_movies.domain.model.top_rated.TopRatedResult
import com.example.movieapp.feature_movies.domain.utils.Resource

interface MoviesRepository {
    suspend fun TopRatedMovies(): Resource<TopRatedResult>


}