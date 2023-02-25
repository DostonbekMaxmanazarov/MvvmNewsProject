package com.example.newsproject.datasource.remote.repository

import com.example.newsproject.datasource.remote.response.ArticlesResponse

interface ISearchRemoteRepository {
    suspend fun getSearchNews(searchText: String): ArticlesResponse
}