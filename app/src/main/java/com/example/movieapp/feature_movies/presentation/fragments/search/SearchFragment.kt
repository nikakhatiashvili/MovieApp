package com.example.movieapp.feature_movies.presentation.fragments.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.example.movieapp.R
import com.example.movieapp.common.extensions.collectFlow
import com.example.movieapp.databinding.HomeFragmentBinding
import com.example.movieapp.databinding.SearchFragmentBinding
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.presentation.BaseFragment
import com.example.movieapp.feature_movies.presentation.fragments.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchFragmentBinding>(SearchFragmentBinding::inflate) {
    private val searchViewModel: SearchViewModel by viewModels()
    override fun start() {
        binding.searchEt.doAfterTextChanged{
            d("iiteasdasd",it.toString())
            searchViewModel.searchs(it.toString())
        }

    }

    override fun observers() {
        collectFlow(searchViewModel.search) {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                        binding.txt.text = it.data.toString()
                }

                is Resource.Error -> {
                        binding.txt.text = it.message
                }
            }
        }
    }


}