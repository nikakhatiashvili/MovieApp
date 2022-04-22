package com.example.movieapp.feature_movies.data.repository

import javax.inject.Inject
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.domain.utils.ResponseHandler
import com.example.movieapp.feature_movies.domain.model.popular.Popular
import com.example.movieapp.feature_movies.data.remote_data.MovieService
import com.example.movieapp.feature_movies.domain.model.latest.UpcomingMovies
import com.example.movieapp.feature_movies.domain.model.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.repository.MoviesRepository

class TopRatedRepositoryImpl @Inject constructor(
    private val apiTopMovie: MovieService,
    private val handleResponse: ResponseHandler
) : MoviesRepository {

    override suspend fun topRatedMovies(): Resource<TopRated> {
        return handleResponse.handleResponse {
            apiTopMovie.getAllTopRatedMovies()
        }
    }

    override suspend fun popularMovies(): Resource<Popular> {
        return handleResponse.handleResponse {
            apiTopMovie.getPopular()
        }
    }
    override suspend fun upcomingMovies(): Resource<UpcomingMovies> {
        return handleResponse.handleResponse {
            apiTopMovie.getUpcoming()
        }
    }
}