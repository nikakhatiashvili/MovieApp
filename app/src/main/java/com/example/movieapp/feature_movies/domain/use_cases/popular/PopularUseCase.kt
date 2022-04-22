package com.example.movieapp.feature_movies.domain.use_cases.popular

import com.example.movieapp.feature_movies.domain.model.popular.Popular
import com.example.movieapp.feature_movies.domain.repository.MoviesRepository
import com.example.movieapp.feature_movies.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PopularUseCase @Inject constructor(private val popularRepository: MoviesRepository) {
    operator fun invoke(): Flow<Resource<Popular>> = flow {
        try {
            emit(Resource.Loading())
            emit(popularRepository.popularMovies())
        } catch (e: Exception) {
            emit(Resource.Error("Exception"))
        }
    }
}