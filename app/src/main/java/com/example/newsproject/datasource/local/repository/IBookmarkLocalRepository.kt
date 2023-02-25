package com.example.newsproject.datasource.local.repository

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity

interface IBookmarkLocalRepository {

    suspend fun getAllCategoryBookmarkNews(): List<BookmarkNewsEntity>

    suspend fun addCategoryBookmarksNews(news: List<BookmarkNewsEntity>)

    suspend fun addBookmarkNews(news: BookmarkNewsEntity)

    suspend fun deleteAllBreakingNews()
}