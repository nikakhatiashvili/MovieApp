package com.example.movieapp.feature_movies.data.remote_data

import com.example.movieapp.feature_movies.domain.model.top_rated.TopRatedResult
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET("movie/top_rated?api_key=7f39984135c9621c058c979457e46b42")
    suspend fun getAllTopRatedMovies(): Response<TopRatedResult>
}