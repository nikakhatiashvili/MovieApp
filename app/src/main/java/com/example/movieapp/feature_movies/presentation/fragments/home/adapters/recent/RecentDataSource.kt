package com.example.movieapp.feature_movies.presentation.fragments.home.adapters.recent

import android.util.Log.d
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingMovies
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingResult
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.InvalidRepoException
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.PopularResult
import com.example.movieapp.feature_movies.domain.use_cases.movie.popular.PopularUseCase
import com.example.movieapp.feature_movies.domain.use_cases.movie.upcoming.UpcomingUseCase
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.presentation.fragments.home.viewpager.USER_STARTING_PAGE_INDEX

const val USER_STARTING_PAGE_INDEXS = 1
class RecentDataSource(
    private val searchRepositoryUseCase: UpcomingUseCase
) :
    PagingSource<Int, UpcomingResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpcomingResult> {
        val position = params.key ?: USER_STARTING_PAGE_INDEXS
        return when (val repos = searchRepositoryUseCase(page = position)) {
            is Resource.Success -> {
                repos.data?.results.let {
                    LoadResult.Page(
                        data = it ?: emptyList(),
                        prevKey = if (position == USER_STARTING_PAGE_INDEXS) null else position - 1,
                        nextKey = if (it == null || it.isEmpty()) null else position + 1
                    )
                }
            }
            is Resource.Error -> {
                d("repoerror", repos.message.toString())
                LoadResult.Error(InvalidRepoException(repos.message))
            }
            else -> {
                LoadResult.Error(Throwable())
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UpcomingResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}
