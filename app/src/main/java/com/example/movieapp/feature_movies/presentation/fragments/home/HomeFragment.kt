package com.example.movieapp.feature_movies.presentation.fragments.home

import androidx.fragment.app.viewModels
import com.example.movieapp.databinding.HomeFragmentBinding
import com.example.movieapp.feature_movies.presentation.BaseFragment


class HomeFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun setUpFragment() {

    }

}
