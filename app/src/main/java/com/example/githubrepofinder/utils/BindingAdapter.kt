package com.example.githubrepofinder.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("profileImage")
fun ImageView.setImage(url: String?) {
    Glide.with(this.context)
        .load(url ?: "")
        .circleCrop()
        .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
        .into(this);

}