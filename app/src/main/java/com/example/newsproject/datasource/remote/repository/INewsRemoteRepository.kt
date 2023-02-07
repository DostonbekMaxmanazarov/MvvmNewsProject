package com.example.newsproject.datasource.remote.repository

import com.example.newsproject.datasource.remote.response.ArticlesResponse

interface INewsRemoteRepository {
    suspend fun getBreakingNews(): ArticlesResponse
    suspend fun getTopStories(): ArticlesResponse
}