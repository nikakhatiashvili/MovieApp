package com.example.movieapp.feature_movies.data.remote_data


import com.example.movieapp.feature_movies.domain.model.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.utils.Constants.GET_NEWS
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET(GET_NEWS)
    suspend fun getAllTopRatedMovies(): Response<TopRated>
}