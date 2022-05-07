package com.example.movieapp.feature_movies.presentation.fragments.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.extensions.collect
import com.example.movieapp.common.utils.Communication
import com.example.movieapp.feature_movies.domain.model.details.cast.DetailCast
import com.example.movieapp.feature_movies.domain.model.details.movie.DetailMovie
import com.example.movieapp.feature_movies.domain.model.details.similar.DetailsSimilar
import com.example.movieapp.feature_movies.domain.use_cases.detail.DetailsUseCaseClass
import com.example.movieapp.feature_movies.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailUseCaseClass: DetailsUseCaseClass,
    private val dispatchers: com.example.movieapp.common.utils.Dispatchers,
    private val communication: Communication<DetailMoviesResource>
) :
    ViewModel() {

//    private val _details = MutableStateFlow<Resource<DetailMovie>>(Resource.EmptyData())
//    val details: MutableStateFlow<Resource<DetailMovie>> get() = _details
//
//    private val _detailsSimilar = MutableStateFlow<Resource<DetailsSimilar>>(Resource.EmptyData())
//    val detailsSimilar: MutableStateFlow<Resource<DetailsSimilar>> get() = _detailsSimilar
//
//    private val _detailsCast = MutableStateFlow<Resource<DetailCast>>(Resource.EmptyData())
//    val detailsCast: MutableStateFlow<Resource<DetailCast>> get() = _detailsCast

    fun getDetails(id: Int) {
        dispatchers.launchBackground(viewModelScope) {
            collect(detailUseCaseClass.detailMovieUseCaseUseCase.invoke(id)) {
                communication.map(DetailMoviesResource.DetailsMovies(it))
            }
        }
    }

    fun getSimilarMovies(id: Int) {
        dispatchers.launchBackground(viewModelScope) {
            collect(detailUseCaseClass.similarMoviesUseCase.invoke(id)) {
                communication.map(DetailMoviesResource.SimilarDetails(it))
            }
        }
    }

    fun getMovieCast(id: Int) {
        dispatchers.launchBackground(viewModelScope) {
            collect(detailUseCaseClass.detailMovieCast.invoke(id)) {
                communication.map(DetailMoviesResource.DetailsCast(it))
            }
        }
    }

    fun collectDetails(collector: FlowCollector<DetailMoviesResource>) {
        viewModelScope.launch {
            communication.collectMovies(collector)
        }
    }
}


sealed class DetailMoviesResource {
    object Loading : DetailMoviesResource()
    object Empty : DetailMoviesResource()
    class DetailsMovies(val detailMovies: Resource<DetailMovie>) : DetailMoviesResource()
    class SimilarDetails(val similarDetails: Resource<DetailsSimilar>) : DetailMoviesResource()
    class DetailsCast(val detailsCast: Resource<DetailCast>) : DetailMoviesResource()
}