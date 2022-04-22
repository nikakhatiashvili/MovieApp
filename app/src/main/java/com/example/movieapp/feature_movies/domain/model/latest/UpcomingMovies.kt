package com.example.movieapp.feature_movies.domain.model.latest

data class UpcomingMovies(
    val page: Int,
    val results: List<UpcomingResult>
)