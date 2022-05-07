package com.example.movieapp.common.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movieapp.R

fun ImageView.setImage(url:String?) {
    Glide.with(context).load(url).placeholder(R.drawable.ic_launcher_background).into(this)
}
