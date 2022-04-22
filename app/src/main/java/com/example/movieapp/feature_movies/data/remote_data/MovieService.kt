package com.example.movieapp.feature_movies.data.remote_data


import com.example.movieapp.feature_movies.domain.model.latest.UpcomingMovies
import com.example.movieapp.feature_movies.domain.model.popular.Popular
import com.example.movieapp.feature_movies.domain.model.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.utils.Constants.GET_POPULAR_MOVIES
import com.example.movieapp.feature_movies.domain.utils.Constants.GET_TOP_MOVIES
import com.example.movieapp.feature_movies.domain.utils.Constants.GET_UPCOMING_MOVIES
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {

    @GET(GET_TOP_MOVIES)
    suspend fun getAllTopRatedMovies(): Response<TopRated>

    @GET(GET_POPULAR_MOVIES)
    suspend fun getPopular(): Response<Popular>

    @GET(GET_UPCOMING_MOVIES)
    suspend fun getUpcoming(): Response<UpcomingMovies>
}