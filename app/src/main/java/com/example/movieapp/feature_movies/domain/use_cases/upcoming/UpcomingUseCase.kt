package com.example.movieapp.feature_movies.domain.use_cases.upcoming

import com.example.movieapp.feature_movies.domain.model.latest.UpcomingMovies
import com.example.movieapp.feature_movies.domain.model.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.repository.MoviesRepository
import com.example.movieapp.feature_movies.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class UpcomingUseCase @Inject constructor(private val MovieRepository: MoviesRepository) {
    operator fun invoke(): Flow<Resource<UpcomingMovies>> = flow {
        try {
            emit(Resource.Loading())
            emit(MovieRepository.upcomingMovies())
        } catch (e: Exception) {
            emit(Resource.Error("Exception"))
        }
    }
}