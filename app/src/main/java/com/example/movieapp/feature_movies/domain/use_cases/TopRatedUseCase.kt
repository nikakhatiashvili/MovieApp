package com.example.movieapp.feature_movies.domain.use_cases


import com.example.movieapp.feature_movies.domain.model.top_rated.TopRatedResult
import com.example.movieapp.feature_movies.domain.repository.MoviesRepository
import com.example.movieapp.feature_movies.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TopRatedUseCase(private val authRepository: MoviesRepository) {
     operator fun invoke(): Flow<Resource<TopRatedResult>> = flow {
        emit(Resource.Loading())
        emit(authRepository.TopRatedMovies())
    }
}