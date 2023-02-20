package com.example.newsproject.datasource.local.repository.impl

import com.example.newsproject.datasource.local.dao.BreakingNewsDao
import com.example.newsproject.datasource.local.dao.TopNewsDao
import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.datasource.local.entity.TopNewsEntity
import com.example.newsproject.datasource.local.repository.ICategoryNewsLocalRepository
import com.example.newsproject.datasource.local.repository.ITopNewsLocalRepository
import javax.inject.Inject

class NewsLocalRepositoryImpl @Inject constructor(
    private val topNewsDao: TopNewsDao, private val breakingNewsDao: BreakingNewsDao
) : ITopNewsLocalRepository, ICategoryNewsLocalRepository {

    override suspend fun getAllCategoryNews(): List<CategoryNewsEntity> =
        breakingNewsDao.getAllBreakingNews()

    override suspend fun addCategoryNews(news: List<CategoryNewsEntity>) =
        breakingNewsDao.addBreakingNews(news)

    override suspend fun deleteAllBreakingNews() = breakingNewsDao.deleteAllBreakingNews()

    override suspend fun addTopNews(news: List<TopNewsEntity>) = topNewsDao.addTopNews(news)

    override suspend fun getAllTopNews(): List<TopNewsEntity> = topNewsDao.getAllTopNews()

    override suspend fun deleteAllTopNews() = topNewsDao.deleteAllTopNews()

}