package com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated

data class TopRated(
    val page: Double?,
    val results: List<TopRatedMovies>?,
    val total_pages: Int?,
    val total_results: Int?
)