package com.example.movieapp.feature_movies.domain.use_cases.movie.upcoming

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.domain.repository.movie_repo.MoviesRepository
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingMovies
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.Popular

class UpcomingUseCase @Inject constructor(private val MovieRepository: MoviesRepository) {
    suspend operator fun invoke(page:Int): Resource<UpcomingMovies>  {
        return MovieRepository.upcomingMovies(page)
    }
}