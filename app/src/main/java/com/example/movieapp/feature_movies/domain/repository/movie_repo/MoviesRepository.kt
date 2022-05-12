package com.example.movieapp.feature_movies.domain.repository.movie_repo

import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.genres.GenresItem
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.Popular
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingMovies

interface MoviesRepository {
    suspend fun topRatedMovies(page:Int): Resource<TopRated>
    suspend fun genres(): Resource<GenresItem>
    suspend fun popularMovies(page:Int): Resource<Popular>
    suspend fun upcomingMovies(page:Int): Resource<UpcomingMovies>
}