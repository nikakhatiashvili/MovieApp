package com.example.movieapp.feature_movies.domain.use_cases.details.detailsInfo

import com.example.movieapp.feature_movies.domain.model.details.movie.DetailMovie
import com.example.movieapp.feature_movies.domain.repository.details_repo.DetailsRepository
import com.example.movieapp.feature_movies.domain.repository.search_repo.SearchRepository
import com.example.movieapp.feature_movies.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailsUseCase @Inject constructor(
    private val searchRepository: DetailsRepository
) {
    operator fun invoke(id: Int): Flow<Resource<DetailMovie>> = flow {
        try {
            emit(Resource.Loading())
            emit(searchRepository.getDetails(id))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}