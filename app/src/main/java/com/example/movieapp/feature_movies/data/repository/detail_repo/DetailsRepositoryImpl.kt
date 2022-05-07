package com.example.movieapp.feature_movies.data.repository.detail_repo

import com.example.movieapp.feature_movies.data.remote_data.details.DetailsService
import com.example.movieapp.feature_movies.domain.model.details.cast.DetailCast
import com.example.movieapp.feature_movies.domain.model.details.movie.DetailMovie
import com.example.movieapp.feature_movies.domain.model.details.similar.DetailsSimilar
import com.example.movieapp.feature_movies.domain.repository.details_repo.DetailsRepository
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.domain.utils.ProvideResponseHandler
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val details: DetailsService,
    private val handleResponse: ProvideResponseHandler
): DetailsRepository {

    override suspend fun getDetails(id: Int): Resource<DetailMovie> {
        return handleResponse.handleResponse {
            details.getSearch(id)
        }
    }

    override suspend fun getSimilarMovies(id: Int): Resource<DetailsSimilar> {
        return handleResponse.handleResponse {
            details.getSimilarMovies(id)
        }
    }
    override suspend fun getMoviesCast(id: Int): Resource<DetailCast> {
        return handleResponse.handleResponse {
            details.getMoviesCast(id)
        }
    }

}