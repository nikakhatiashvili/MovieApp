package com.example.movieapp.feature_movies.domain.use_cases.movie.top_rated

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.repository.movie_repo.MoviesRepository

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