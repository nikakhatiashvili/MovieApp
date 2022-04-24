package com.example.movieapp.feature_movies.domain.repository.search_repo

import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.Popular
import com.example.movieapp.feature_movies.domain.model.search.Search
import com.example.movieapp.feature_movies.domain.utils.Constants.GET_SEARCH
import com.example.movieapp.feature_movies.domain.utils.Resource
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRepository {

    suspend fun getSearch(query: String): Resource<Search>
}