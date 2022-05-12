package com.example.movieapp.feature_movies.data.remote_data.movie_tv_shows


import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.genres.GenresItem
import retrofit2.http.GET
import retrofit2.Response
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.Popular
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingMovies
import com.example.movieapp.feature_movies.domain.utils.Constants.GET_MOVIES_GENRE
import com.example.movieapp.feature_movies.domain.utils.Constants.GET_TOP_MOVIES
import com.example.movieapp.feature_movies.domain.utils.Constants.GET_POPULAR_MOVIES
import com.example.movieapp.feature_movies.domain.utils.Constants.GET_UPCOMING_MOVIES
import retrofit2.http.Query

interface MovieService {

    @GET(GET_TOP_MOVIES)
    suspend fun getAllTopRatedMovies(@Query("page") page: Int): Response<TopRated>

    @GET(GET_MOVIES_GENRE)
    suspend fun getGenres(): Response<GenresItem>

    @GET(GET_POPULAR_MOVIES)
    suspend fun getPopular(@Query("page") page:Int): Response<Popular>

    @GET(GET_UPCOMING_MOVIES)
    suspend fun getUpcoming(@Query("page") page: Int): Response<UpcomingMovies>
}