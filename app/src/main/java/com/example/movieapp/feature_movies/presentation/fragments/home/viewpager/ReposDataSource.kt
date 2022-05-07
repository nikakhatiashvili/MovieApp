package com.example.movieapp.feature_movies.presentation.fragments.home.viewpager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.InvalidRepoException
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.Popular
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.PopularResult
import com.example.movieapp.feature_movies.domain.use_cases.movie.popular.PopularUseCase
import com.example.movieapp.feature_movies.domain.use_cases.movie.top_rated.TopRatedUseCase
import com.example.movieapp.feature_movies.domain.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

const val USER_STARTING_PAGE_INDEX = 1

class ReposDataSource(
    private val searchRepositoryUseCase: PopularUseCase
) :
    PagingSource<Int, PopularResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularResult> {
        val position = params.key ?: USER_STARTING_PAGE_INDEX
        return when (val repos = searchRepositoryUseCase( page = position)) {
            is Resource.Success -> {
                repos.data?.results.let {
                    LoadResult.Page(
                        data = it ?: emptyList(),
                        prevKey = if (position == USER_STARTING_PAGE_INDEX) null else position - 1,
                        nextKey = if (it == null || it.isEmpty()) null else position + 1
                    )
                }
            }
            is Resource.Error -> {
                LoadResult.Error(InvalidRepoException(repos.message))
            }
            else -> {
                LoadResult.Error(Throwable())
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PopularResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}