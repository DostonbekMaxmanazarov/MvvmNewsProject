package com.example.newsproject.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsproject.datasource.local.dao.BreakingNewsDao
import com.example.newsproject.datasource.local.dao.TopNewsDao
import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.datasource.local.entity.TopNewsEntity
import com.example.newsproject.util.Constants.DATABASE_VERSION

@Database(
    entities = [CategoryNewsEntity::class, TopNewsEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun topNewsDao(): TopNewsDao
    abstract fun breakingNewsDao(): BreakingNewsDao
}