package com.example.newsproject.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsproject.datasource.local.entity.BreakingNewsEntity
import com.example.newsproject.datasource.local.entity.TopNewsEntity
import com.example.newsproject.util.Constants.BREAKING_NEWS_TABLE
import com.example.newsproject.util.Constants.TOP_NEWS_TABLE

@Dao
interface BreakingNewsDao {
    @Query("SELECT * FROM $BREAKING_NEWS_TABLE")
    suspend fun getAllTopNews(): List<BreakingNewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopNews(news: List<BreakingNewsEntity>)

    @Query("DELETE FROM $TOP_NEWS_TABLE")
    suspend fun deleteAllTopNews()
}