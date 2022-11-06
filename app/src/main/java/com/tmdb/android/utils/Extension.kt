package com.tmdb.android.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tmdb.android.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun Context.toast(text: String?) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.activeButton(button: Button) {
    button.setBackgroundColor(
        ContextCompat.getColor(
            this,
            R.color.green_2
        )
    )
    button.setTextColor(
        ContextCompat.getColor(
            this,
            R.color.black_3
        )
    )
}

fun Context.inactiveButton(button: Button) {
    button.setBackgroundColor(
        ContextCompat.getColor(
            this,
            R.color.black_2
        )
    )
    button.setTextColor(
        ContextCompat.getColor(
            this,
            R.color.white_1
        )
    )
}

fun ImageView.loadPhotoUrl(
    url: String,
) {
    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(R.color.black_2)
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

@OptIn(DelicateCoroutinesApi::class)
fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    GlobalScope.launch {
        delay(10)
        imm.showSoftInput(this@showKeyboard, 0)
    }
}