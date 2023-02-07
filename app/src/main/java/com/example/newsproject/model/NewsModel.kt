package com.example.newsproject.model

data class NewsModel(
    val name: String? = null,
    val title: String? = null,
    val imageUrl: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
) : BaseNewsModel()
