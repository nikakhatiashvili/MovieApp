package com.example.movieapp.feature_movies.data.repository.search_repo

import com.example.movieapp.feature_movies.data.remote_data.movie_tv_shows.MovieService
import com.example.movieapp.feature_movies.data.remote_data.search.SearchService
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingMovies
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.Popular
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.model.search.Search
import com.example.movieapp.feature_movies.domain.repository.movie_repo.MoviesRepository
import com.example.movieapp.feature_movies.domain.repository.search_repo.SearchRepository
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.domain.utils.ResponseHandler
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiTopMovie: SearchService,
    private val handleResponse: ResponseHandler
) : SearchRepository {

    override suspend fun getSearch(query: String): Resource<Search> {
        return handleResponse.handleResponse {
            apiTopMovie.getSearch(query)
        }
    }
}