package com.example.movieapp.feature_movies.presentation.fragments.home

import android.provider.CalendarContract
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import dagger.hilt.android.AndroidEntryPoint
import com.example.movieapp.common.extensions.collectFlow
import com.example.movieapp.databinding.HomeFragmentBinding

import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.presentation.BaseFragment
import com.example.movieapp.feature_movies.presentation.fragments.home.adapters.genres.GenresAdapter
import com.example.movieapp.feature_movies.presentation.fragments.home.adapters.rated.TopRatedAdapter
import com.example.movieapp.feature_movies.presentation.fragments.home.adapters.recent.RecentAdapter
import com.example.movieapp.feature_movies.presentation.fragments.home.adapters.recent.RepositoryLoadStateAdapter
import com.example.movieapp.feature_movies.presentation.fragments.home.viewpager.HorizontalMarginItemDecoration
import com.example.movieapp.feature_movies.presentation.fragments.home.viewpager.ViewPagerAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var viewPager: ViewPagerAdapter
    private  var upcomingAdapter = RecentAdapter()
    private  lateinit var genresAdapter : GenresAdapter
    private   var topRatedAdapter =TopRatedAdapter()

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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.state.collectLatest {
                    viewPager.submitData(it)
                }
            }
        }
    }
    private fun setGenres(){
        genresAdapter = GenresAdapter()
        binding.genresRecyclerview.apply {
            adapter = genresAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        }
        collectFlow(homeViewModel.genres) {
            when (it) {
                is Resource.Loading -> {
                    binding.spinKit.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    genresAdapter.data = it.data?.genres!!
                    binding.spinKit.visibility = View.GONE
                }
                is Resource.Error -> {
//                        binding.tvText.text = "ERROR"
                }
            }
        }
    }
    private fun setTopRatedAdapter(){
        binding.ratedRecyclerview.apply {
            adapter = topRatedAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = topRatedAdapter.withLoadStateFooter(
                footer = RepositoryLoadStateAdapter { topRatedAdapter.retry() },
            )
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.rated.collectLatest {
                    topRatedAdapter.submitData(it)
                }
            }
        }
    }
    private fun setUpcomingAdapter(){
        binding.upcomingRecyclerview.apply {
            adapter = upcomingAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = upcomingAdapter.withLoadStateFooter(
                footer = RepositoryLoadStateAdapter { upcomingAdapter.retry() },
            )
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.upcoming.collectLatest {
                    upcomingAdapter.submitData(it)
                }
            }
        }
    }


    override fun observers() {
        setViewPager()
        setUpcomingAdapter()
        setGenres()
        setTopRatedAdapter()
    }

    override fun start() {
        navigateToDetails()
    }

    private fun navigateToDetails() {

    }

}
