package com.example.movieapp.feature_movies.domain.model.top_rated

data class TopRated(
    val page: Double?,
    val results: List<TopRatedMovies>?,
)