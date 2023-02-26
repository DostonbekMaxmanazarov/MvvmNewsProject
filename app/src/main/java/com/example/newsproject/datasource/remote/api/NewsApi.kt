package com.example.newsproject.datasource.remote.api

import com.example.newsproject.datasource.remote.response.ArticlesResponse
import com.example.newsproject.util.Constants.API_KEY
import com.example.newsproject.util.Constants.EVERY_THING
import com.example.newsproject.util.Constants.TOP_HEADING
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(TOP_HEADING)
    suspend fun getCategoryNews(
        @Query("category") category: String,
        //@Query("country") country: String = "us",
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = API_KEY
    ): ArticlesResponse

    @GET(EVERY_THING)
    suspend fun getSearchNews(
        @Query("q") searchText: String,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = API_KEY
    ): ArticlesResponse
}