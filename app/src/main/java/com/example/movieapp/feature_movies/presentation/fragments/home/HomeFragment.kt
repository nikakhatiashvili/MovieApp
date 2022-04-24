package com.example.movieapp.feature_movies.presentation.fragments.home

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.example.movieapp.common.extensions.collectFlow
import com.example.movieapp.databinding.HomeFragmentBinding
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.presentation.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()


    override fun observers() {
        collectFlow(homeViewModel.movies) {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                        binding.tvText.text = it.data.toString()
                }

                is Resource.Error -> {
//                        binding.tvText.text = "ERROR"
                }
            }
        }
        collectFlow(homeViewModel.popularMovies) {
            when(it){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
//                    binding.tvText.text = it.data.toString()
                }
                is Resource.Error ->{
//                    binding.tvText.text = "ERROR"
                }
            }
        }
        collectFlow(homeViewModel.upcomingMovies) {
            when(it){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
//                    binding.tvText.text = it.data.toString()
                }
                is Resource.Error ->{
                    binding.tvText.text = "ERROR"
                }
            }
        }
    }

    override fun start() {

    }

}
