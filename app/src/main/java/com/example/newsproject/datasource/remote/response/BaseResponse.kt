package com.example.newsproject.datasource.remote.response
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val status: String,
    val totalResults: String,
    val articles: List<T>?
)
