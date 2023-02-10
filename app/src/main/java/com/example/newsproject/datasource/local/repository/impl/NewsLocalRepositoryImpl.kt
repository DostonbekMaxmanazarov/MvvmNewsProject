package com.example.newsproject.datasource.local.repository.impl

import com.example.newsproject.datasource.local.dao.BreakingNewsDao
import com.example.newsproject.datasource.local.dao.TopNewsDao
import com.example.newsproject.datasource.local.entity.BreakingNewsEntity
import com.example.newsproject.datasource.local.entity.TopNewsEntity
import com.example.newsproject.datasource.local.repository.IBreakingNewsLocalRepository
import com.example.newsproject.datasource.local.repository.ITopNewsLocalRepository
import javax.inject.Inject

class NewsLocalRepositoryImpl @Inject constructor(
    private val topNewsDao: TopNewsDao, private val breakingNewsDao: BreakingNewsDao
) : ITopNewsLocalRepository, IBreakingNewsLocalRepository {

    override suspend fun getAllBreakingNews(): List<BreakingNewsEntity> =
        breakingNewsDao.getAllBreakingNews()

    override suspend fun addBreakingNews(news: List<BreakingNewsEntity>) =
        breakingNewsDao.addBreakingNews(news)

    override suspend fun deleteAllBreakingNews() = breakingNewsDao.deleteAllBreakingNews()

    override suspend fun addTopNews(news: List<TopNewsEntity>) = topNewsDao.addTopNews(news)

    override suspend fun getAllTopNews(): List<TopNewsEntity> = topNewsDao.getAllTopNews()

    override suspend fun deleteAllTopNews() = topNewsDao.deleteAllTopNews()

}