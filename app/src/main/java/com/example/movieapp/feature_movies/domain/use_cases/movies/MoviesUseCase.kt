package com.example.movieapp.feature_movies.domain.use_cases.movies

import com.example.movieapp.feature_movies.domain.use_cases.popular.PopularUseCase
import com.example.movieapp.feature_movies.domain.use_cases.top_rated.TopRatedUseCase
import com.example.movieapp.feature_movies.domain.use_cases.upcoming.UpcomingUseCase

data class MoviesUseCase(
    val topRatedUseCase: TopRatedUseCase,
    val popularUseCase: PopularUseCase,
    val upcomingUseCase: UpcomingUseCase,
)
