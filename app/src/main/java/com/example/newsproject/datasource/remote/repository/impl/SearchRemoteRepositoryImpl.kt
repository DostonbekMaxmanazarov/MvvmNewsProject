package com.example.newsproject.datasource.remote.repository.impl

import com.example.newsproject.datasource.remote.api.NewsApi
import com.example.newsproject.datasource.remote.repository.ICategoryRemoteRepository
import com.example.newsproject.datasource.remote.repository.ISearchRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticlesResponse
import javax.inject.Inject

class SearchRemoteRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : ISearchRemoteRepository {
    override suspend fun getSearchNews(searchText: String): ArticlesResponse = newsApi.getSearchNews(searchText)
}