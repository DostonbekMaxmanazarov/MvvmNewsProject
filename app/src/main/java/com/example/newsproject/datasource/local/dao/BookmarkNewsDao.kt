package com.example.newsproject.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.util.Constants.BOOKMARK_NEWS_TABLE

@Dao
interface BookmarkNewsDao {
    @Query("SELECT * FROM $BOOKMARK_NEWS_TABLE")
    suspend fun getAllBookmarkNews(): List<BookmarkNewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmarksNews(news: List<BookmarkNewsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmarkNews(news: BookmarkNewsEntity)

    @Query("DELETE FROM $BOOKMARK_NEWS_TABLE")
    suspend fun deleteAllBookmarkNews()

    @Query("DELETE FROM $BOOKMARK_NEWS_TABLE WHERE id=:id")
    suspend fun deleteBookmark(id: Long)
}