package com.example.newsproject.datasource.remote.repository.impl

import com.example.newsproject.datasource.remote.api.NewsApi
import com.example.newsproject.datasource.remote.repository.ICategoryRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticlesResponse
import javax.inject.Inject

class CategoryRemoteRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : ICategoryRemoteRepository {
    override suspend fun getCategoryNews(category: String): ArticlesResponse =
        newsApi.getCategoryNews(category)
}