package com.example.movieapp.feature_movies.data.remote_data.details

import com.example.movieapp.feature_movies.domain.model.details.cast.DetailCast
import com.example.movieapp.feature_movies.domain.model.details.movie.DetailMovie
import com.example.movieapp.feature_movies.domain.model.details.similar.DetailsSimilar
import com.example.movieapp.feature_movies.domain.model.search.Search
import com.example.movieapp.feature_movies.domain.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsService {

    @GET(Constants.DETAILS_MOVIE)
    suspend fun getSearch(
        @Path("movie_id") id: Int,
    ): Response<DetailMovie>

    @GET(Constants.DETAILS_SIMILAR)
    suspend fun getSimilarMovies(
        @Path("movie_id") id: Int,
    ): Response<DetailsSimilar>

    @GET(Constants.DETAILS_MOVIE_CAST)
    suspend fun getMoviesCast(
        @Path("movie_id") id: Int,
    ): Response<DetailCast>
}