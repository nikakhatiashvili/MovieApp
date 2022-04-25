package com.example.movieapp.feature_movies.data.remote_data.details

import com.example.movieapp.feature_movies.domain.model.details.movie.DetailMovie
import com.example.movieapp.feature_movies.domain.model.search.Search
import com.example.movieapp.feature_movies.domain.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsService {

    @GET(Constants.DETAILS_ID)
    suspend fun getSearch(
        @Path("movie_id") id: Int,
    ): Response<DetailMovie>
}