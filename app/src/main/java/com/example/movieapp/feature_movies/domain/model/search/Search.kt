package com.example.movieapp.feature_movies.domain.model.search

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Search(
    val page: Int?,
    val results: List<Result>?,
    val total_pages: Int?,
    val total_results: Int?
): Parcelable