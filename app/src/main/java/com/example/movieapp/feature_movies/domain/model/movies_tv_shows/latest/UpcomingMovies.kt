package com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest

data class UpcomingMovies(
    val page: Int,
    val results: List<UpcomingResult>
)