package com.example.newsproject.datasource.local.repository

import com.example.newsproject.datasource.local.entity.CategoryNewsEntity

interface ICategoryLocalRepository {

    suspend fun getAllCategoryNews(): List<CategoryNewsEntity>

    suspend fun addCategoryNews(news: List<CategoryNewsEntity>)

    suspend fun deleteAllBreakingNews()
}