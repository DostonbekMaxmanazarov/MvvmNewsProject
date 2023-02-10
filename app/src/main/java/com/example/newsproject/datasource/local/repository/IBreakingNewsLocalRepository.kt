package com.example.newsproject.datasource.local.repository

import com.example.newsproject.datasource.local.entity.BreakingNewsEntity
import com.example.newsproject.datasource.local.entity.TopNewsEntity

interface IBreakingNewsLocalRepository {

    suspend fun getAllBreakingNews(): List<BreakingNewsEntity>

    suspend fun addBreakingNews(news: List<BreakingNewsEntity>)

    suspend fun deleteAllBreakingNews()
}