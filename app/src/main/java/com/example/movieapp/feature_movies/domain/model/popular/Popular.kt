package com.example.movieapp.feature_movies.domain.model.popular

data class Popular(
    val page: Int?,
    val results: List<PopularResult>?,
    val total_pages: Int?,
    val total_results: Int?
)