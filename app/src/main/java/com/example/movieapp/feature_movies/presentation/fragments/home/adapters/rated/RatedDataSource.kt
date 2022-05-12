package com.example.movieapp.feature_movies.presentation.fragments.home.adapters.rated

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingResult
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.InvalidRepoException
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRatedMovies
import com.example.movieapp.feature_movies.domain.use_cases.movie.top_rated.TopRatedUseCase
import com.example.movieapp.feature_movies.domain.use_cases.movie.upcoming.UpcomingUseCase
import com.example.movieapp.feature_movies.domain.utils.Resource


const val USER_STARTING_PAGE_INDEXSS = 1

class RatedDataSource(
    private val searchRepositoryUseCase: TopRatedUseCase
) :
    PagingSource<Int, TopRatedMovies>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopRatedMovies> {
        val position = params.key ?: USER_STARTING_PAGE_INDEXSS
        return when (val repos = searchRepositoryUseCase(page = position)) {
            is Resource.Success -> {
                repos.data?.results.let {
                    LoadResult.Page(
                        data = it ?: emptyList(),
                        prevKey = if (position == USER_STARTING_PAGE_INDEXSS) null else position - 1,
                        nextKey = if (it == null || it.isEmpty()) null else position + 1
                    )
                }
            }
            is Resource.Error -> {
                Log.d("repoerror", repos.message.toString())
                LoadResult.Error(InvalidRepoException(repos.message))
            }
            else -> {
                LoadResult.Error(Throwable())
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TopRatedMovies>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}

