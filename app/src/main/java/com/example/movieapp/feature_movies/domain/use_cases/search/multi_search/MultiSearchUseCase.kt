package com.example.movieapp.feature_movies.domain.use_cases.search.multi_search

import android.util.Log.d
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.Popular
import com.example.movieapp.feature_movies.domain.model.search.Search
import com.example.movieapp.feature_movies.domain.repository.movie_repo.MoviesRepository
import com.example.movieapp.feature_movies.domain.repository.search_repo.SearchRepository
import com.example.movieapp.feature_movies.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MultiSearchUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    operator fun invoke(search:String): Flow<Resource<Search>> = flow {
        try {
            emit(Resource.Loading())
            emit(searchRepository.getSearch(search))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}