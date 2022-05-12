package com.example.movieapp.feature_movies.presentation.fragments.home

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.movieapp.common.extensions.collect
import com.example.movieapp.common.utils.Dispatchers
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.genres.GenresItem

import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingResult
import com.example.movieapp.feature_movies.domain.utils.Resource

import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.PopularResult
import com.example.movieapp.feature_movies.domain.use_cases.movie.movies.MoviesUseCase
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRatedMovies
import com.example.movieapp.feature_movies.presentation.fragments.home.adapters.rated.RatedDataSource

import com.example.movieapp.feature_movies.presentation.fragments.home.adapters.recent.RecentDataSource
import com.example.movieapp.feature_movies.presentation.fragments.home.viewpager.ReposDataSource
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val dispatchers: Dispatchers
) : ViewModel() {

    private val _genres = MutableStateFlow<Resource<GenresItem>>(Resource.EmptyData())
    val genres: MutableStateFlow<Resource<GenresItem>> get() = _genres

    private val _state =
        MutableStateFlow<PagingData<PopularResult>>(PagingData.empty())
    val state get() = _state.asStateFlow()

    private val _rated =
        MutableStateFlow<PagingData<TopRatedMovies>>(PagingData.empty())
    val rated get() = _rated.asStateFlow()
    private val _upcoming =
        MutableStateFlow<PagingData<UpcomingResult>>(PagingData.empty())
    val upcoming get() = _upcoming.asStateFlow()

    init {
        getMovies()
        getPopularMovies()
        getUpcomingMovies()
        getGenre()
    }

    private fun getGenre() {
        dispatchers.launchBackground(viewModelScope) {
            collect(moviesUseCase.genresUseCase()) {
                _genres.value = it
            }
        }
    }

    private fun getMovies() {
        dispatchers.launchBackground(viewModelScope) {
            Pager(config = PagingConfig(pageSize = 1, enablePlaceholders = false),
                pagingSourceFactory = {
                    RatedDataSource(
                        moviesUseCase.topRatedUseCase
                    )
                }
            ).flow.cachedIn(viewModelScope).collectLatest {
                _rated.value = it
            }
        }
    }

    private fun getPopularMovies() {
        dispatchers.launchBackground(viewModelScope) {
            Pager(config = PagingConfig(pageSize = 1, enablePlaceholders = false),
                pagingSourceFactory = {
                    ReposDataSource(
                        moviesUseCase.popularUseCase
                    )
                }
            ).flow.cachedIn(viewModelScope).collectLatest {
                _state.value = it
            }
        }
    }

    private fun getUpcomingMovies() {
        dispatchers.launchBackground(viewModelScope) {
            Pager(config = PagingConfig(pageSize = 1, enablePlaceholders = false),
                pagingSourceFactory = {
                    RecentDataSource(
                        moviesUseCase.upcomingUseCase
                    )
                }
            ).flow.cachedIn(viewModelScope).collectLatest {
                _upcoming.value = it
            }
        }

    }
}