package com.example.newsproject.datasource.remote.repository.impl

import com.example.newsproject.datasource.remote.api.NewsApi
import com.example.newsproject.datasource.remote.repository.INewsRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticlesResponse
import javax.inject.Inject

class NewsRemoteRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : INewsRemoteRepository {
    override suspend fun getBreakingNews(): ArticlesResponse = newsApi.getBreakingNews()

    override suspend fun getTopStories(): ArticlesResponse = newsApi.getTopStories()
}