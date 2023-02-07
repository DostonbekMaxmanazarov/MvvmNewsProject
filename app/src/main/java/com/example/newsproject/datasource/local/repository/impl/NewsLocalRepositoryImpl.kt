package com.example.newsproject.datasource.local.repository.impl

import com.example.newsproject.datasource.remote.api.NewsApi
import com.example.newsproject.datasource.remote.repository.INewsRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticlesResponse

class NewsLocalRepositoryImpl constructor(
    private val newsApi: NewsApi
) : INewsRemoteRepository {
    override suspend fun getBreakingNews(): ArticlesResponse = newsApi.getBreakingNews()

    override suspend fun getTopStories(): ArticlesResponse = newsApi.getTopStories()
}