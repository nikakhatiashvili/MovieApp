package com.example.movieapp.feature_movies.presentation.fragments.home.viewpager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.Popular
import com.example.movieapp.feature_movies.domain.use_cases.movie.top_rated.TopRatedUseCase
import com.example.movieapp.feature_movies.domain.utils.Resource

//const val USER_STARTING_PAGE_INDEX = 1
//
//class ReposDataSource(
//    private val query: String,
//    private val searchRepositoryUseCase: TopRatedUseCase
//) :
//    PagingSource<Int, Popular>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Popular> {
//        val position = params.key ?: USER_STARTING_PAGE_INDEX
//        return when (val repos = searchRepositoryUseCase(page = position)) {
//            is Resource.Success<*> -> {
//                repos.data?.repositoryItems.let {
//                    LoadResult.Page(
//                        data = it ?: emptyList(),
//                        prevKey = if (position == USER_STARTING_PAGE_INDEX) null else position - 1,
//                        nextKey = if (it == null || it.isEmpty()) null else position + 1
//                    )
//                }
//            }
//            is Resource.Error -> {
//                LoadResult.Error(InvalidRepoException(repos.message))
//            }
//            else -> {
//                LoadResult.Error(Throwable())
//            }
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, RepositoryItem>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }
//
//}