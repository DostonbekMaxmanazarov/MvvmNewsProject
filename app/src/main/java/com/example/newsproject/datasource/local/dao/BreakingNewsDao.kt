package com.example.newsproject.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.util.Constants.BREAKING_NEWS_TABLE

@Dao
interface BreakingNewsDao {
    @Query("SELECT * FROM $BREAKING_NEWS_TABLE")
    suspend fun getAllBreakingNews(): List<CategoryNewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBreakingNews(news: List<CategoryNewsEntity>)

    @Query("DELETE FROM $BREAKING_NEWS_TABLE")
    suspend fun deleteAllBreakingNews()
}