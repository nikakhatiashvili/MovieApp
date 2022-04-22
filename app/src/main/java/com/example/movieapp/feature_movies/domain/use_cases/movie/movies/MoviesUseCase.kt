package com.example.movieapp.feature_movies.domain.use_cases.movie.movies

import com.example.movieapp.feature_movies.domain.use_cases.movie.popular.PopularUseCase
import com.example.movieapp.feature_movies.domain.use_cases.movie.top_rated.TopRatedUseCase
import com.example.movieapp.feature_movies.domain.use_cases.movie.upcoming.UpcomingUseCase

data class MoviesUseCase(
    val topRatedUseCase: TopRatedUseCase,
    val popularUseCase: PopularUseCase,
    val upcomingUseCase: UpcomingUseCase,

)
