package com.example.movieapp.feature_movies.domain.model.search

data class Search(
    val page: Int?,
    val results: List<Result>?,
    val total_pages: Int?,
    val total_results: Int?
)