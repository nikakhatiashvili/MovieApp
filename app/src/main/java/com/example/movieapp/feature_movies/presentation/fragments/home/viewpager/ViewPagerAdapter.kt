package com.example.movieapp.feature_movies.presentation.fragments.home.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.common.extensions.setImage
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.PopularResult
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRatedMovies

class ViewPagerAdapter :
    PagingDataAdapter<PopularResult, ViewPagerAdapter.ViewPagerViewHolder>(REPOSITORY_COMPARATOR) {

//    var data: List<PopularResult> = emptyList()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    companion object {
        private val REPOSITORY_COMPARATOR =
            object : DiffUtil.ItemCallback<PopularResult>() {
                override fun areItemsTheSame(
                    oldItem: PopularResult,
                    newItem: PopularResult
                ) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: PopularResult,
                    newItem: PopularResult
                ) =
                    oldItem == newItem
            }
    }

    inner class ViewPagerViewHolder(var binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo:PopularResult) {

            binding.imageView.setImage("https://image.tmdb.org/t/p/original/" + repo.poster_path)
//            binding.tvTitle.text = curNames.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentRepo = getItem(position)
        holder.bind(currentRepo!!)
    }
}