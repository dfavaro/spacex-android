package com.danielefavaro.spacex.base.ktx

import java.text.SimpleDateFormat
import java.util.*

fun String.parseDate(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val parse: Date? = parser.parse(this)
    return parse?.let { formatter.format(it) } ?: this
}