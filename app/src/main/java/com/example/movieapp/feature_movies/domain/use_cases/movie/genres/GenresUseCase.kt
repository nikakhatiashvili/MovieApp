package com.example.movieapp.feature_movies.domain.use_cases.movie.genres

import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.genres.GenresItem
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.repository.movie_repo.MoviesRepository
import com.example.movieapp.feature_movies.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GenresUseCase @Inject constructor(private val movieRepository: MoviesRepository) {
    operator fun invoke(): Flow<Resource<GenresItem>> = flow {
        try {
            emit(Resource.Loading())
            emit(movieRepository.genres())
        } catch (e: Exception) {
            emit(Resource.Error("Exception"))
        }
    }
}
