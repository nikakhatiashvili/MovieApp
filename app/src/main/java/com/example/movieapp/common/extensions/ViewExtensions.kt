package com.example.movieapp.common.extensions

import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.Glide
import com.example.movieapp.R

fun ImageView.glide(url:String?) {
    Glide.with(context).load(url).placeholder(R.drawable.ic_launcher_background).into(this)
}

fun ImageView.koinLoad(url: String) {
    this.load(url) {
        placeholder(R.drawable.ic_launcher_foreground)
        crossfade(true)
        crossfade(500)
        transformations(RoundedCornersTransformation(10f))
    }
}