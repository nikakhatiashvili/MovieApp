package com.example.movieapp.feature_movies.data.repository

import com.example.movieapp.feature_movies.data.remote_data.MovieService
import com.example.movieapp.feature_movies.domain.model.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.repository.MoviesRepository
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.domain.utils.ResponseHandler
import javax.inject.Inject

class TopRatedRepositoryImpl @Inject constructor(
    private val apiTopMovie: MovieService,
    private val handleResponse: ResponseHandler
) : MoviesRepository {

    override suspend fun topRatedMovies(): Resource<TopRated> {
        return handleResponse.handleResponse {
            apiTopMovie.getAllTopRatedMovies()
        }
    }
}