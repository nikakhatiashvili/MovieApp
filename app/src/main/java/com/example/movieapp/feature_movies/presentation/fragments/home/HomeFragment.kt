package com.example.movieapp.feature_movies.presentation.fragments.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import dagger.hilt.android.AndroidEntryPoint
import com.example.movieapp.common.extensions.collectFlow
import com.example.movieapp.databinding.HomeFragmentBinding

import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.presentation.BaseFragment
import com.example.movieapp.feature_movies.presentation.fragments.home.viewpager.HorizontalMarginItemDecoration
import com.example.movieapp.feature_movies.presentation.fragments.home.viewpager.ViewPagerAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var viewPager: ViewPagerAdapter

    private fun setViewPager() {
        viewPager = ViewPagerAdapter()
        val viewPagerView = binding.viewPager
        viewPagerView.adapter = viewPager
        viewPagerView.offscreenPageLimit = 1
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * abs(position))
        }
        viewPagerView.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        viewPagerView.addItemDecoration(itemDecoration)
        collectFlow(homeViewModel.movies) {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {

                }

                is Resource.Error -> {
//                        binding.tvText.text = "ERROR"
                }
            }
        }

    }


    override fun observers() {
        setViewPager()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.state.collectLatest {
                    viewPager.submitData(it)
                }
            }
        }
        collectFlow(homeViewModel.upcomingMovies) {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
//                    binding.tvText.text = it.data.toString()
                }
                is Resource.Error -> {
//                    binding.tvText.text = "ERROR"
                }
            }
        }
    }

    override fun start() {
        navigateToDetails()
    }

    private fun navigateToDetails() {

    }

}
