package com.example.movieapp.feature_movies.data.remote_data.search

import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingMovies
import com.example.movieapp.feature_movies.domain.model.search.Search
import com.example.movieapp.feature_movies.domain.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET(Constants.GET_UPCOMING_MOVIES)
    suspend fun getSearch(@Query("query")search: String): Response<Search>
}