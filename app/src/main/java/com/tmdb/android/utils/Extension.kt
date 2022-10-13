package com.tmdb.android.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadPhotoUrl(
    url: String,
) {
    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
        .clearOnDetach()
}

fun String.withDateFormat(): String {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = format.parse(this) as Date
    return DateFormat.getDateInstance(DateFormat.DEFAULT).format(date)
}