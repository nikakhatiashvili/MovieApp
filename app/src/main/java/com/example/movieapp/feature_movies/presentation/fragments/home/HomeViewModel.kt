package com.example.movieapp.feature_movies.presentation.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.feature_movies.domain.model.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.use_cases.TopRatedUseCase
import com.example.movieapp.feature_movies.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val topRatedUseCase: TopRatedUseCase) : ViewModel() {

    private val _movies = MutableStateFlow<Resource<TopRated>>(Resource.EmptyData())
    val movies: MutableStateFlow<Resource<TopRated>> get() = _movies

    fun getMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                topRatedUseCase.invoke().collectLatest {
                    _movies.value = it
                }
            }
        }
    }
}