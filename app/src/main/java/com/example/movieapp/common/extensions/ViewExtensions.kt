package com.example.movieapp.common.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import coil.load
import coil.transform.RoundedCornersTransformation

fun ImageView.setImage(url:String?) {
    Glide.with(context).load(url).placeholder(R.drawable.loading).into(this)
}
fun ImageView.koinLoad(url: String) {
    this.load(url) {
        placeholder(R.drawable.loading_anim)
//        load(R.drawable.loading_anim)
        crossfade(true)
        crossfade(500)
        transformations(RoundedCornersTransformation(10f))
    }
}