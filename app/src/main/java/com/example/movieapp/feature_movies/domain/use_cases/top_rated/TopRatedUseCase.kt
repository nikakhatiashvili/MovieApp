package com.example.movieapp.feature_movies.domain.use_cases.top_rated

import com.example.movieapp.feature_movies.domain.model.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.repository.MoviesRepository
import com.example.movieapp.feature_movies.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TopRatedUseCase @Inject constructor(private val movieRepository: MoviesRepository) {
    operator fun invoke(): Flow<Resource<TopRated>> = flow {
        try {
            emit(Resource.Loading())
            emit(movieRepository.topRatedMovies())
        } catch (e: Exception) {
            emit(Resource.Error("Exception"))
        }
    }
}