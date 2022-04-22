package com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated

data class TopRatedMovies(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Double>?,
    val id: Double?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Double?
)