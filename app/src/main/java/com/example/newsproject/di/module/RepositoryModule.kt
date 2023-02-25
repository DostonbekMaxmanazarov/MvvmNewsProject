package com.example.newsproject.di.module

import com.example.newsproject.datasource.local.dao.BookmarkNewsDao
import com.example.newsproject.datasource.local.dao.CategoryNewsDao
import com.example.newsproject.datasource.local.repository.IBookmarkLocalRepository
import com.example.newsproject.datasource.local.repository.ICategoryLocalRepository
import com.example.newsproject.datasource.local.repository.impl.BookmarkLocalRepositoryImpl
import com.example.newsproject.datasource.local.repository.impl.CategoryLocalRepositoryImpl
import com.example.newsproject.datasource.remote.api.NewsApi
import com.example.newsproject.datasource.remote.repository.ICategoryRemoteRepository
import com.example.newsproject.datasource.remote.repository.ISearchRemoteRepository
import com.example.newsproject.datasource.remote.repository.impl.CategoryRemoteRepositoryImpl
import com.example.newsproject.datasource.remote.repository.impl.SearchRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCategoryRemoteRepository(api: NewsApi): ICategoryRemoteRepository = CategoryRemoteRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideSearchRemoteRepository(api: NewsApi): ISearchRemoteRepository = SearchRemoteRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideCategoryNewsLocalRepository(
        categoryNewsDao: CategoryNewsDao
    ): ICategoryLocalRepository = CategoryLocalRepositoryImpl(categoryNewsDao)

    @Provides
    @Singleton
    fun provideBookmarkLocalRepository(
        bookmarkNewsDao: BookmarkNewsDao
    ): IBookmarkLocalRepository = BookmarkLocalRepositoryImpl(bookmarkNewsDao)
}