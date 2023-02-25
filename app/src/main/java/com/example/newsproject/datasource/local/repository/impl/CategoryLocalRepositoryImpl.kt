package com.example.newsproject.datasource.local.repository.impl

import com.example.newsproject.datasource.local.dao.CategoryNewsDao
import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.datasource.local.repository.ICategoryLocalRepository
import javax.inject.Inject

class CategoryLocalRepositoryImpl @Inject constructor(
    private val breakingNewsDao: CategoryNewsDao
): ICategoryLocalRepository {

    override suspend fun getAllCategoryNews(): List<CategoryNewsEntity> =
        breakingNewsDao.getAllBreakingNews()

    override suspend fun addCategoryNews(news: List<CategoryNewsEntity>) =
        breakingNewsDao.addBreakingNews(news)

    override suspend fun deleteAllBreakingNews() = breakingNewsDao.deleteAllBreakingNews()

}