package com.example.movieapp.feature_movies.presentation.fragments.search

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.common.extensions.collectFlow
import com.example.movieapp.databinding.SearchFragmentBinding
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchFragmentBinding>(SearchFragmentBinding::inflate) {
    private val searchViewModel: SearchViewModel by viewModels()

    override fun start() {
        multiSearch()
        binding.button2.setOnClickListener {
            navigateToDetails()
        }
    }

    override fun observers() {
        searchViewModel.collectSearchFlow {
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

    private fun multiSearch() {
        binding.searchEt.doAfterTextChanged { queryInput ->
            searchViewModel.searchCase(queryInput.toString())
        }
    }

    private fun navigateToDetails() {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(10195)
        findNavController().navigate(action)
    }


}