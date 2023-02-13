package com.example.newsproject.datasource.local.repository

import com.example.newsproject.datasource.local.entity.BreakingNewsEntity

interface IBreakingNewsLocalRepository {

    suspend fun getAllBreakingNews(): List<BreakingNewsEntity>

    suspend fun addBreakingNews(news: List<BreakingNewsEntity>)

    suspend fun deleteAllBreakingNews()
}