package com.example.movieapp.feature_movies.domain.use_cases.detail

import com.example.movieapp.feature_movies.domain.model.details.movie.DetailMovie
import com.example.movieapp.feature_movies.domain.model.details.similar.DetailsSimilar
import com.example.movieapp.feature_movies.domain.repository.details_repo.DetailsRepository
import com.example.movieapp.feature_movies.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SimilarMoviesUseCase @Inject constructor(
    private val searchRepository: DetailsRepository
) {
    operator fun invoke(id: Int): Flow<Resource<DetailsSimilar>> = flow {
        try {
            emit(Resource.Loading())
            emit(searchRepository.getSimilarMovies(id))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}
