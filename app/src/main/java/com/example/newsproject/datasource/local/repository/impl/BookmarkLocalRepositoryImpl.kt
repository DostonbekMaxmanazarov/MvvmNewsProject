package com.example.newsproject.datasource.local.repository.impl

import com.example.newsproject.datasource.local.dao.BookmarkNewsDao
import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.datasource.local.repository.IBookmarkLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkLocalRepositoryImpl @Inject constructor(
    private val bookmarkNewsDao: BookmarkNewsDao
) : IBookmarkLocalRepository {
    override fun getAllBookmarkNews(): Flow<List<BookmarkNewsEntity>> =
        bookmarkNewsDao.getAllBookmarkNews()

    override suspend fun addBookmarksNews(news: List<BookmarkNewsEntity>) =
        bookmarkNewsDao.addBookmarksNews(news)

    override suspend fun addBookmarkNews(news: BookmarkNewsEntity): Long =
        bookmarkNewsDao.addBookmarkNews(news)

    override suspend fun deleteAllBookmarkNews() = bookmarkNewsDao.deleteAllBookmarkNews()

    override suspend fun deleteBookmark(id: Int) = bookmarkNewsDao.deleteBookmark(id)
}