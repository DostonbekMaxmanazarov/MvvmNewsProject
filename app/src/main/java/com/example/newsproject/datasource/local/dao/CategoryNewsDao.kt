package com.example.newsproject.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.util.Constants.CATEGORY_NEWS_TABLE

@Dao
interface CategoryNewsDao {
    @Query("SELECT * FROM $CATEGORY_NEWS_TABLE")
    suspend fun getAllCategoryNews(): List<CategoryNewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategoryNews(news: List<CategoryNewsEntity>)

    @Query("DELETE FROM $CATEGORY_NEWS_TABLE")
    suspend fun deleteAllCategoryNews()
}