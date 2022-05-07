package com.example.movieapp.feature_movies.presentation.fragments.home.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.common.extensions.setImage
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.PopularResult
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRatedMovies

class ViewPagerAdapter() : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    var data: List<PopularResult> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewPagerViewHolder(var binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            val curNames = data[bindingAdapterPosition]
            binding.imageView.setImage("https://image.tmdb.org/t/p/original/" + curNames.poster_path)
//            binding.tvTitle.text = curNames.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind()
    }
}