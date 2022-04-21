package com.example.movieapp.feature_movies.domain.model.top_rated

data class TopRated(
    val page: Int?,
    val results: List<TopRatedResult>?,
    val total_pages: Int?,
    val total_results: Int?
)