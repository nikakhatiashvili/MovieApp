package com.example.movieapp.feature_movies.presentation.fragments.search

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.extensions.collect
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingMovies
import com.example.movieapp.feature_movies.domain.model.search.Search
import com.example.movieapp.feature_movies.domain.use_cases.movie.movies.MoviesUseCase
import com.example.movieapp.feature_movies.domain.use_cases.search.search.SearchUseCase
import com.example.movieapp.feature_movies.domain.utils.DelayProvider
import com.example.movieapp.feature_movies.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val dispatchers: com.example.movieapp.common.utils.Dispatchers,
    private val delay: DelayProvider
) : ViewModel() {

    private val _search = MutableStateFlow<Resource<Search>>(Resource.EmptyData())
    val search: MutableStateFlow<Resource<Search>> get() = _search

    private var searchJob: Job? = null

    fun searchCase(userInput: String) {
        searchJob?.cancel()
        searchJob = dispatchers.launchBackground(viewModelScope) {
            delay.provideDelay(delay.getDelayTime())
            collect(searchUseCase.searchUseCase(userInput)) {
                _search.value = it
            }
        }
    }
}