package com.example.newsproject.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.toDateFormatted(format: String = "MMMM dd, yyyy"): String =
    SimpleDateFormat(format, Locale.US).format(this)