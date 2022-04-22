package com.example.movieapp.feature_movies.presentation.fragments.home

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.movieapp.common.extensions.collect
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.domain.model.popular.Popular
import com.example.movieapp.feature_movies.domain.use_cases.MoviesUseCase
import com.example.movieapp.feature_movies.domain.model.top_rated.TopRated

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val dispatchers: com.example.movieapp.common.utils.Dispatchers
) : ViewModel() {

    private val _movies = MutableStateFlow<Resource<TopRated>>(Resource.EmptyData())
    val movies: MutableStateFlow<Resource<TopRated>> get() = _movies

    private val _popularMovies = MutableStateFlow<Resource<Popular>>(Resource.EmptyData())
    val popularMovies: MutableStateFlow<Resource<Popular>> get() = _popularMovies

    init {
        getMovies()
        getPopularMovies()
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
}