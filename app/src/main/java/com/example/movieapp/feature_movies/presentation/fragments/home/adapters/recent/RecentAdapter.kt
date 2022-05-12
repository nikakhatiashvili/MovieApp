package com.example.movieapp.feature_movies.presentation.fragments.home.adapters.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.common.extensions.koinLoad
import com.example.movieapp.databinding.UpcomingItemBinding
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingResult


class RecentAdapter()  :
    PagingDataAdapter<UpcomingResult, RecentAdapter.ViewPagerViewHolder>(REPOSITORY_COMPARATORS) {



    companion object {
        private val REPOSITORY_COMPARATORS =
            object : DiffUtil.ItemCallback<UpcomingResult>() {
                override fun areItemsTheSame(
                    oldItem: UpcomingResult,
                    newItem: UpcomingResult
                ) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: UpcomingResult,
                    newItem: UpcomingResult
                ) =
                    oldItem == newItem
            }
    }

    inner class ViewPagerViewHolder(var binding: UpcomingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo:UpcomingResult) {


            binding.imageView.koinLoad("https://image.tmdb.org/t/p/original/" + repo.poster_path)
//            binding.tvTitle.text = curNames.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = UpcomingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentRepo = getItem(position)
        holder.bind(currentRepo!!)
    }
}
