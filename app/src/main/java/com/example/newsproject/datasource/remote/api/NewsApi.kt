package com.example.newsproject.datasource.remote.api

import com.example.newsproject.datasource.remote.response.ArticlesResponse
import com.example.newsproject.util.Constants.API_KEY
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET
    suspend fun getBreakingNews(
        @Query("country") country: String = "us",
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = API_KEY
    ): ArticlesResponse

    @GET
    suspend fun getTopStories(
        @Query("country") country: String = "us",
        @Query("category") sortBy: String = "technology",
        @Query("apiKey") apiKey: String = API_KEY
    ): ArticlesResponse
}