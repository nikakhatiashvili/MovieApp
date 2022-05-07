package com.example.movieapp.feature_movies.presentation.fragments.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.extensions.collect
import com.example.movieapp.common.utils.Communication
import com.example.movieapp.feature_movies.domain.model.search.Search
import com.example.movieapp.feature_movies.domain.use_cases.search.SearchUseCaseClass
import com.example.movieapp.feature_movies.domain.utils.ProvideDelay
import com.example.movieapp.feature_movies.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCaseClass,
    private val dispatchers: com.example.movieapp.common.utils.Dispatchers,
    private val delay: ProvideDelay,
    private val communication: Communication<Resource<Search>>
) : ViewModel() {

    private var searchJob: Job? = null

    fun searchCase(userInput: String) {
        searchJob?.cancel()
        searchJob = dispatchers.launchBackground(viewModelScope) {
            delay.provideDelay(delay.getDelayTime())
            collect(searchUseCase.searchUseCase(userInput)) {
                communication.map(it)
            }
        }
    }

    fun collectSearchFlow(collector: FlowCollector<Resource<Search>>) = viewModelScope.launch {
        communication.collectMovies(collector)
    }

}