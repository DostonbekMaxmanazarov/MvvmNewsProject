package com.example.newsproject.util.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.toDateFormatted(format: String = "dd.MM.yyyy"): String =
    SimpleDateFormat(format, Locale.US).format(this)