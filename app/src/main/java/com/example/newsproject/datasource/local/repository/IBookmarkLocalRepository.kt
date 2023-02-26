package com.example.newsproject.datasource.local.repository

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity

interface IBookmarkLocalRepository {

    suspend fun getAllBookmarkNews(): List<BookmarkNewsEntity>

    suspend fun addBookmarksNews(news: List<BookmarkNewsEntity>)

    suspend fun addBookmarkNews(news: BookmarkNewsEntity):Long

    suspend fun deleteAllBookmarkNews()
}