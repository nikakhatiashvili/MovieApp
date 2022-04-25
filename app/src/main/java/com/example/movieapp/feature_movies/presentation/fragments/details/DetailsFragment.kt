package com.example.movieapp.feature_movies.presentation.fragments.details

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieapp.common.extensions.collectFlow
import com.example.movieapp.databinding.DetailsFragmentBinding
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsFragmentBinding>(DetailsFragmentBinding::inflate) {

    private val argsArticle: DetailsFragmentArgs by navArgs()
    private val detailsViewModel: DetailsViewModel by viewModels()
    override fun start() {
        d("AAAA", argsArticle.id.toString())
        detailsViewModel.getDetails(argsArticle.id)
        detailsViewModel.getSimilarMovies(argsArticle.id)
        detailsViewModel.getMovieCast(argsArticle.id)
    }

    override fun observers() {
        collectFlow(detailsViewModel.details) {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
//                    d("AAAAAAAAA", it.data.toString())
                }
            }
        }
//        collectFlow(detailsViewModel.detailsSimilar) {
//            when (it) {
//                is Resource.Loading -> {
//
//                }
//                is Resource.Success -> {
//                    d("AAAAAAAAA", it.data.toString())
//                }
//            }
//        }
        collectFlow(detailsViewModel.detailsCast) {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    d("AAAAAAAAA", it.data.toString())
                }
            }
        }
    }

}