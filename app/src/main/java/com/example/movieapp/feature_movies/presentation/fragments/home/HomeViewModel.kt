package com.example.movieapp.feature_movies.presentation.fragments.home

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.movieapp.common.extensions.collect
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingMovies
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.Popular
import com.example.movieapp.feature_movies.domain.use_cases.movie.movies.MoviesUseCase
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRated
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val dispatchers: com.example.movieapp.common.utils.Dispatchers
) : ViewModel() {

    private val _movies = MutableStateFlow<Resource<TopRated>>(Resource.EmptyData())
    val movies: MutableStateFlow<Resource<TopRated>> get() = _movies

    private val _popularMovies = MutableStateFlow<Resource<Popular>>(Resource.EmptyData())
    val popularMovies: MutableStateFlow<Resource<Popular>> get() = _popularMovies

    private val _upcomingMovies = MutableStateFlow<Resource<UpcomingMovies>>(Resource.EmptyData())
    val upcomingMovies: MutableStateFlow<Resource<UpcomingMovies>> get() = _upcomingMovies

    init {
//        getMovies()
//        getPopularMovies()
//        getUpcomingMovies()
        launchMovies()
    }

    private fun getMovies() {
        dispatchers.launchBackground(viewModelScope) {
            collect(moviesUseCase.topRatedUseCase()) {
                _movies.value = it
            }
        }
    }

    private fun getPopularMovies() {
        dispatchers.launchBackground(viewModelScope) {
            collect(moviesUseCase.popularUseCase()) {
                _popularMovies.value = it
            }
        }
    }

    private fun getUpcomingMovies() {
        dispatchers.launchBackground(viewModelScope) {
            collect(moviesUseCase.upcomingUseCase()) {
                _upcomingMovies.value = it
            }
        }
    }

    private fun launchMovies() {
        dispatchers.launchBackground(viewModelScope) {

            launch {
                collect(moviesUseCase.topRatedUseCase()) {
                    _movies.value = it
                }
            }

            launch {
                collect(moviesUseCase.popularUseCase()) {
                    _popularMovies.value = it
                }
            }

            launch {
                collect(moviesUseCase.upcomingUseCase()) {
                    _upcomingMovies.value = it
                }
            }
        }
    }
}