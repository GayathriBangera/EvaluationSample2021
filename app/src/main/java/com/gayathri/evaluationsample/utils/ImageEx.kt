package com.gayathri.evaluationsample.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gayathri.evaluationsample.R

fun ImageView.setImage(
    imageUrl: String
) {
    Glide.with(this)
        .load(imageUrl)
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .into(this)
}
