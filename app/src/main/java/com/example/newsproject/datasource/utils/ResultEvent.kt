package com.example.newsproject.datasource.utils

sealed class ResultEvent<out T> {
    data class Success<out T>(val data: T) : ResultEvent<T>()
    data class Failure(val message: String?) : ResultEvent<Nothing>()
    object Loading : ResultEvent<Nothing>()
}