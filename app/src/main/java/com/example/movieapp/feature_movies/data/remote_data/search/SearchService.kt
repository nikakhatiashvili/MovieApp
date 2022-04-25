package com.example.movieapp.feature_movies.data.remote_data.search

import com.example.movieapp.feature_movies.domain.model.search.Search
import com.example.movieapp.feature_movies.domain.utils.Constants.GET_SEARCH
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET(GET_SEARCH)
    suspend fun getSearch(
        @Query("query") query: String,
    ): Response<Search>
}