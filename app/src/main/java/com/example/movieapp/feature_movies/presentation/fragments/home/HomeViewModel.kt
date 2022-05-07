package com.example.movieapp.feature_movies.presentation.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.extensions.collect
import com.example.movieapp.common.utils.Communication
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingMovies
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.Popular
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.use_cases.movie.movies.MoviesUseCase
import com.example.movieapp.feature_movies.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val dispatchers: com.example.movieapp.common.utils.Dispatchers,
    private val communication: Communication<FullResource>
) : ViewModel() {

    init {
        getMovies()
    }

    private fun getMovies() {
        dispatchers.launchBackground(viewModelScope) {

            collect(moviesUseCase.popularUseCase()) {
                communication.map(FullResource.PopularMovies(it))
            }

            collect(moviesUseCase.topRatedUseCase()) {
                communication.map(FullResource.Movies(it))
            }


            collect(moviesUseCase.upcomingUseCase()) {
                communication.map(FullResource.Upcoming(it))
            }
        }
    }

    fun collectState(collector: FlowCollector<FullResource>) = viewModelScope.launch {
        communication.collectMovies(collector)
    }


}

sealed class FullResource {
    object Empty : FullResource()
    object Loading : FullResource()
    class Movies(val first: Resource<TopRated>) : FullResource()
    class PopularMovies(val second: Resource<Popular>) : FullResource()
    class Upcoming(val third: Resource<UpcomingMovies>) : FullResource()
}