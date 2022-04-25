package com.example.movieapp.feature_movies.presentation.fragments.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.extensions.collect
import com.example.movieapp.feature_movies.domain.model.search.Search
import com.example.movieapp.feature_movies.domain.use_cases.search.SearchUseCaseClass
import com.example.movieapp.feature_movies.domain.utils.DelayProvider
import com.example.movieapp.feature_movies.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCaseClass,
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