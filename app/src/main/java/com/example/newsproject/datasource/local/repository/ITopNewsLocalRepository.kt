package com.example.newsproject.datasource.local.repository

import com.example.newsproject.datasource.local.entity.TopNewsEntity

interface ITopNewsLocalRepository {

    suspend fun addTopNews(news: List<TopNewsEntity>)

    suspend fun getAllTopNews(): List<TopNewsEntity>

    suspend fun deleteAllTopNews()
}