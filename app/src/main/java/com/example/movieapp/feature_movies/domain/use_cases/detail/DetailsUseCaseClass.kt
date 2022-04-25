package com.example.movieapp.feature_movies.domain.use_cases.detail

data class DetailsUseCaseClass(
    val detailMovieUseCaseUseCase: DetailMovieUseCaseUseCase,
    val similarMoviesUseCase: SimilarMoviesUseCase,
    val detailMovieCast: DetailMovieCastUseCase
    )
