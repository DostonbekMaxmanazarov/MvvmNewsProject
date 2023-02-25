package com.example.newsproject.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsproject.datasource.local.dao.BookmarkNewsDao
import com.example.newsproject.datasource.local.dao.CategoryNewsDao
import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.util.Constants.DATABASE_VERSION

@Database(
    entities = [CategoryNewsEntity::class, BookmarkNewsEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun categoryNewsDao(): CategoryNewsDao
    abstract fun bookmarkNewsDao(): BookmarkNewsDao
}