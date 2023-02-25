package com.example.newsproject.datasource.local.repository.impl

import com.example.newsproject.datasource.local.dao.BookmarkNewsDao
import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.datasource.local.repository.IBookmarkLocalRepository
import javax.inject.Inject

class BookmarkLocalRepositoryImpl @Inject constructor(
    private val bookmarkNewsDao: BookmarkNewsDao
) : IBookmarkLocalRepository {
    override suspend fun getAllCategoryBookmarkNews(): List<BookmarkNewsEntity> =
        bookmarkNewsDao.getAllBookmarkNews()

    override suspend fun addCategoryBookmarksNews(news: List<BookmarkNewsEntity>) =
        bookmarkNewsDao.addBookmarksNews(news)

    override suspend fun addBookmarkNews(news: BookmarkNewsEntity) =
        bookmarkNewsDao.addBookmarkNews(news)

    override suspend fun deleteAllBreakingNews() = bookmarkNewsDao.deleteAllBookmarkNews()
}