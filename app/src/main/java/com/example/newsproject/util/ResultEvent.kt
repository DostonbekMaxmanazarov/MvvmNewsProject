package com.example.newsproject.util

sealed class ResultEvent<out T> {
    data class Success<out T>(val data: T) : ResultEvent<T>()
    data class Error(val message: String?) : ResultEvent<Nothing>()
    object Loading : ResultEvent<Nothing>()
}