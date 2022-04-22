package com.example.movieapp.feature_movies.domain.use_cases

data class MoviesUseCase(
    val topRatedUseCase: TopRatedUseCase,
    val popularUseCase: PopularUseCase,
)
