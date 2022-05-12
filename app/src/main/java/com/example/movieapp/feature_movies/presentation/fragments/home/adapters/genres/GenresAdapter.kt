package com.example.movieapp.feature_movies.presentation.fragments.home.adapters.genres

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.GenresItemBinding
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.genres.Genre

class GenresAdapter : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    var count = 0

    var sience = "Sience"
    var data: List<Genre> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenresAdapter.ViewHolder {
        return ViewHolder(
            GenresItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: GenresAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(val binding: GenresItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var currentData: Genre
        fun bind() {
            currentData = data[absoluteAdapterPosition]
            if (count == 0){
                binding.constraint.setBackgroundColor(Color.parseColor("#21C6F6"))
                count += 1
            }else if (count == 1){
                binding.constraint.setBackgroundColor(Color.parseColor("#16E31F"))
                count += 1
            }else if (count == 2){
                binding.constraint.setBackgroundColor(Color.parseColor("#E30E4A"))
                count += 1
            }else if (count == 3){
                binding.constraint.setBackgroundColor(Color.parseColor("#E8DE29"))
                count = 0
            }
            if (currentData.id == 878){
                binding.text.text = sience
            }else{
                binding.text.text = currentData.name
            }
        }
    }

    override fun getItemCount() = data.size

}