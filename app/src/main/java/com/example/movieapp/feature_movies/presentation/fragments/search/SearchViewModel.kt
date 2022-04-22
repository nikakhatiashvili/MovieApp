package com.example.movieapp.feature_movies.presentation.fragments.search

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.extensions.collect
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingMovies
import com.example.movieapp.feature_movies.domain.model.search.Search
import com.example.movieapp.feature_movies.domain.use_cases.movie.movies.MoviesUseCase
import com.example.movieapp.feature_movies.domain.use_cases.search.search.SearchUseCase
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
    private val dispatchers: com.example.movieapp.common.utils.Dispatchers
) : ViewModel() {

    private val _search = MutableStateFlow<Resource<Search>>(Resource.EmptyData())
    val search: MutableStateFlow<Resource<Search>> get() = _search


    @OptIn(FlowPreview::class)
    fun search(search:String){
        dispatchers.launchBackground(viewModelScope) {
            searchUseCase.searchUseCase(search).collectLatest {
                d("searchResult",it.toString())
                _search.value = it
            }
        }
    }
    private var searchJob: Job? = null

    fun searchs(userInput: String) {
        searchJob?.cancel() // cancel previous job when user enters new letter
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(300)      // add some delay before search, this function checks if coroutine is canceled, if it is canceled it won't continue execution
            searchUseCase.searchUseCase.invoke(userInput).collectLatest {
                d("searchResult",it.data.toString())
                _search.value = it
            }
        }
    }
}