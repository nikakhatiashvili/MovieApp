package com.example.movieapp.feature_movies.domain.model.details.cast

data class DetailCast(
    val cast: List<Cast>?,
    val crew: List<Crew>?,
    val id: Int?
)