package com.example.newsproject.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchNewsModel(
    val name: String? = null,
    val title: String? = null,
    val imageUrl: String? = null,
    val description: String? = null,
    val publishedAt: String? = null
) : Parcelable
