package com.example.newsproject.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.util.Constants.BOOKMARK_NEWS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkNewsDao {
    @Query("SELECT * FROM $BOOKMARK_NEWS_TABLE")
    fun getAllBookmarkNews(): Flow<List<BookmarkNewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmarksNews(news: List<BookmarkNewsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmarkNews(news: BookmarkNewsEntity): Long

    @Query("DELETE FROM $BOOKMARK_NEWS_TABLE")
    suspend fun deleteAllBookmarkNews()

    @Query("DELETE FROM $BOOKMARK_NEWS_TABLE WHERE id=:id")
    suspend fun deleteBookmark(id: Int)
}