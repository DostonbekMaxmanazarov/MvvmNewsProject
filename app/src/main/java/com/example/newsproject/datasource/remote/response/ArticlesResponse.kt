package com.example.newsproject.datasource.remote.response

import com.example.newsproject.util.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ArticlesResponse(
    val articles: List<ArticleDataResponse>?
)

@Serializable
data class ArticleDataResponse(
    val source: SourceResponse? = null,
    val author: String? = null,
    val title: String? = null,
    val urlToImage: String? = null,
    @SerialName("content") val description: String? = null,
    @Serializable(with = DateSerializer.Nullable::class) val publishedAt: Date? = null
)

@Serializable
data class SourceResponse(
    val id: String? = null, val name: String? = null
)