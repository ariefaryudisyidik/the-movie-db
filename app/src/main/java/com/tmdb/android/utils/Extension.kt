package com.tmdb.android.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun Context.toast(text: String?) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

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
    var result = "-"
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    if (this != "") {
        val date = format.parse(this) as Date
        result = DateFormat.getDateInstance(DateFormat.DEFAULT).format(date)
    }
    return result
}