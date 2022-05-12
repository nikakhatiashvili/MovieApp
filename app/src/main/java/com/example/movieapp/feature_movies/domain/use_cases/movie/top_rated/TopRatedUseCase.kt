package com.example.movieapp.feature_movies.domain.use_cases.movie.top_rated


import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.repository.movie_repo.MoviesRepository
import javax.inject.Inject


class TopRatedUseCase @Inject constructor(private val MovieRepository: MoviesRepository) {
    suspend operator fun invoke(page: Int): Resource<TopRated> {
        return MovieRepository.topRatedMovies(page)
    }
}
