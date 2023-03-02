package com.example.newsproject.datasource.local.repository

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import kotlinx.coroutines.flow.Flow

interface IBookmarkLocalRepository {

    fun getAllBookmarkNews(): Flow<List<BookmarkNewsEntity>>

    suspend fun addBookmarksNews(news: List<BookmarkNewsEntity>)

    suspend fun addBookmarkNews(news: BookmarkNewsEntity): Long

    suspend fun deleteAllBookmarkNews()

    suspend fun deleteBookmark(id: Int)
}