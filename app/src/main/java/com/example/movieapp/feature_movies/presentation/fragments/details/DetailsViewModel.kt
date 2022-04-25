package com.example.movieapp.feature_movies.presentation.fragments.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.extensions.collect
import com.example.movieapp.feature_movies.domain.model.details.movie.DetailMovie
import com.example.movieapp.feature_movies.domain.model.details.similar.DetailsSimilar
import com.example.movieapp.feature_movies.domain.use_cases.detail.DetailsUseCaseClass
import com.example.movieapp.feature_movies.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailUseCaseClass: DetailsUseCaseClass,
    private val dispatchers: com.example.movieapp.common.utils.Dispatchers
) :
    ViewModel() {

    private val _details = MutableStateFlow<Resource<DetailMovie>>(Resource.EmptyData())
    val details: MutableStateFlow<Resource<DetailMovie>> get() = _details

    private val _detailsSimilar = MutableStateFlow<Resource<DetailsSimilar>>(Resource.EmptyData())
    val detailsSimilar: MutableStateFlow<Resource<DetailsSimilar>> get() = _detailsSimilar

    fun getDetails(id: Int) {
        dispatchers.launchBackground(viewModelScope) {
            collect(detailUseCaseClass.detailsUseCase.invoke(id)) {
                _details.value = it
            }
        }
    }
    fun getSimilarMovies(id:Int){
        dispatchers.launchBackground(viewModelScope) {
            collect(detailUseCaseClass.similarMoviesUseCase.invoke(id)) {
                _detailsSimilar.value = it
            }
        }
    }
}
