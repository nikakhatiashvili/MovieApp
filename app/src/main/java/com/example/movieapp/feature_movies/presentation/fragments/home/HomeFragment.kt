package com.example.movieapp.feature_movies.presentation.fragments.home

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.example.movieapp.common.extensions.collectFlow
import com.example.movieapp.databinding.HomeFragmentBinding
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.Popular
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.presentation.BaseFragment
import kotlinx.coroutines.flow.FlowCollector

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun start() {
        navigateToDetails()
    }

    private fun navigateToDetails() {

    }


    override fun observers() {
        homeViewModel.collectState {
            when (it) {
                is FullResource.Movies -> handleMovies(it.first)
                is FullResource.PopularMovies -> handleUpComing(it.second)
            }
        }
    }


    private fun handleMovies(item: Resource<TopRated>) {
        when (item) {
            is Resource.Loading -> {

            }
            is Resource.Success -> {
                binding.tvText.text = item.data?.results?.get(1)!!.title
            }

            is Resource.Error -> {

            }
        }
    }

    private fun handleUpComing(item: Resource<Popular>) {
        when (item) {
            is Resource.Loading -> {

            }
            is Resource.Success -> {
                binding.tvText2.text = item.data?.results?.get(0)!!.title
            }
            is Resource.Error -> {

            }
        }
    }

}
