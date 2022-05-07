package com.example.movieapp.feature_movies.data.repository.movie_repo

import javax.inject.Inject
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.domain.utils.ProvideResponseHandler
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.Popular
import com.example.movieapp.feature_movies.data.remote_data.movie_tv_shows.MovieService
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingMovies
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.repository.movie_repo.MoviesRepository

class TopRatedRepositoryImpl @Inject constructor(
    private val apiTopMovie: MovieService,
    private val handleResponse: ProvideResponseHandler
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