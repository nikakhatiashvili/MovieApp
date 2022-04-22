package com.example.movieapp.feature_movies.domain.model.latest

import android.os.Parcelable


data class UpcomingMovies(
    val page: Int,
    val results: List<UpcomingResult>
)