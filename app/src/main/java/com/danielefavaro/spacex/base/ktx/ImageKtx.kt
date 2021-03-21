package com.danielefavaro.spacex.base.ktx

import android.widget.ImageView
import coil.load
import com.danielefavaro.spacex.R

fun ImageView.load(url: String) {
    load(url) {
        crossfade(true)
        placeholder(R.drawable.ic_launcher_foreground) // TODO placeholder
    }
}