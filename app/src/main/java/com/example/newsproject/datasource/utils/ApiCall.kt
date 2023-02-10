package com.example.newsproject.datasource.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T, R, M> apiCall(
    call: suspend () -> Response<T>
): ResultEvent<T> {
    try {
        val response = withContext(Dispatchers.IO) { call.invoke() }

        return if (response.isSuccessful) {
            ResultEvent.Success(response.body()!!)
        } else ResultEvent.Failure(response.errorBody().toString())
    } catch (t: Throwable) {
        return when (t) {
            is HttpException -> {
                ResultEvent.Failure(t.message)
            }
            is IOException -> {
                ResultEvent.Failure(t.message)
            }
            else -> ResultEvent.Failure(t.message)
        }
    }
}