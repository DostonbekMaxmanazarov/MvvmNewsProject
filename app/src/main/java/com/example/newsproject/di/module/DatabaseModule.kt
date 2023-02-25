package com.example.newsproject.di.module

import android.content.Context
import androidx.room.Room
import com.example.newsproject.datasource.local.dao.BookmarkNewsDao
import com.example.newsproject.datasource.local.dao.CategoryNewsDao
import com.example.newsproject.datasource.local.database.NewsDatabase
import com.example.newsproject.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase {
        return Room.databaseBuilder(
            context, NewsDatabase::class.java, DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCategoryNewsDao(database: NewsDatabase): CategoryNewsDao = database.categoryNewsDao()

    @Provides
    @Singleton
    fun provideBookmarkNewsDao(database: NewsDatabase): BookmarkNewsDao = database.bookmarkNewsDao()
}