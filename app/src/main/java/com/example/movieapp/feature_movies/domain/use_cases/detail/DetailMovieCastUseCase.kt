package com.example.movieapp.feature_movies.domain.use_cases.detail

import com.example.movieapp.feature_movies.domain.model.details.cast.DetailCast
import com.example.movieapp.feature_movies.domain.model.details.movie.DetailMovie
import com.example.movieapp.feature_movies.domain.repository.details_repo.DetailsRepository
import com.example.movieapp.feature_movies.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DetailMovieCastUseCase @Inject constructor(
    private val detailRepository: DetailsRepository
) {
    operator fun invoke(id: Int): Flow<Resource<DetailCast>> = flow {
        try {
            emit(Resource.Loading())
            emit(detailRepository.getMoviesCast(id))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}
