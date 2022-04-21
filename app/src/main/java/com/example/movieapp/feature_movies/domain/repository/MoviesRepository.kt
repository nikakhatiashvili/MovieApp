package com.example.movieapp.feature_movies.domain.repository


import com.example.movieapp.feature_movies.domain.model.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.utils.Resource

interface MoviesRepository {
    suspend fun topRatedMovies(): Resource<TopRated>
}