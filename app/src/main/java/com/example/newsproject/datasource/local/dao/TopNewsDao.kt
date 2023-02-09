package com.example.newsproject.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsproject.datasource.local.entity.TopNewsEntity
import com.example.newsproject.util.Constants.BREAKING_NEWS_TABLE
import com.example.newsproject.util.Constants.TOP_NEWS_TABLE

@Dao
interface TopNewsDao {
    @Query("SELECT * FROM $TOP_NEWS_TABLE")
   suspend fun getAllTopNews(): List<TopNewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopNews(news: List<TopNewsEntity>)

    @Query("DELETE FROM $TOP_NEWS_TABLE")
    suspend fun deleteAllTopNews()
}