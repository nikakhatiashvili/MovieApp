package com.example.movieapp.feature_movies.presentation.fragments.home.adapters.rated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.common.extensions.koinLoad
import com.example.movieapp.databinding.RatedItemBinding
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRatedMovies



class TopRatedAdapter() :
    PagingDataAdapter<TopRatedMovies, TopRatedAdapter.ViewPagerViewHolder>(REPOSITORY_COMPARATORS) {


    companion object {
        private val REPOSITORY_COMPARATORS =
            object : DiffUtil.ItemCallback<TopRatedMovies>() {
                override fun areItemsTheSame(
                    oldItem: TopRatedMovies,
                    newItem: TopRatedMovies
                ) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: TopRatedMovies,
                    newItem: TopRatedMovies
                ) =
                    oldItem == newItem
            }
    }

    inner class ViewPagerViewHolder(var binding: RatedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: TopRatedMovies) {
            binding.imageView.koinLoad("https://image.tmdb.org/t/p/original/" + repo.poster_path)
//            binding.tvTitle.text = curNames.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = RatedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentRepo = getItem(position)
        holder.bind(currentRepo!!)
    }
}

