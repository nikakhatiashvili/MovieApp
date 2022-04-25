package com.example.movieapp.feature_movies.domain.repository.details_repo

import com.example.movieapp.feature_movies.domain.model.details.movie.DetailMovie
import com.example.movieapp.feature_movies.domain.model.details.similar.DetailsSimilar
import com.example.movieapp.feature_movies.domain.utils.Resource

interface DetailsRepository {

    suspend fun getDetails(id: Int): Resource<DetailMovie>
    suspend fun getSimilarMovies(id: Int): Resource<DetailsSimilar>
}