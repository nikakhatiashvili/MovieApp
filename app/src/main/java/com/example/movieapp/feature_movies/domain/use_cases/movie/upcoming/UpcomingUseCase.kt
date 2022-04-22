package com.example.movieapp.feature_movies.domain.use_cases.movie.upcoming

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.domain.repository.movie_repo.MoviesRepository
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingMovies

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