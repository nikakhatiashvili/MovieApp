package com.example.movieapp.feature_movies.domain.use_cases

import com.example.movieapp.feature_movies.domain.model.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.repository.MoviesRepository
import com.example.movieapp.feature_movies.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TopRatedUseCase @Inject constructor(private val authRepository: MoviesRepository) {
    operator fun invoke(): Flow<Resource<TopRated>> = flow {
        try {
            emit(Resource.Loading())
            emit(authRepository.topRatedMovies())
        } catch (e: Exception) {
            emit(Resource.Error("Exception"))
        }
    }
}