package com.example.movieapp.feature_movies.domain.use_cases.detail

import com.example.movieapp.feature_movies.domain.use_cases.detail.DetailsUseCase

data class DetailsUseCaseClass(
    val detailsUseCase: DetailsUseCase,
    val similarMoviesUseCase: SimilarMoviesUseCase
    )
