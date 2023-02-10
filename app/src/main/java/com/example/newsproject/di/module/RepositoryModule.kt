package com.example.newsproject.di.module

import com.example.newsproject.datasource.local.dao.BreakingNewsDao
import com.example.newsproject.datasource.local.dao.TopNewsDao
import com.example.newsproject.datasource.local.repository.IBreakingNewsLocalRepository
import com.example.newsproject.datasource.local.repository.ITopNewsLocalRepository
import com.example.newsproject.datasource.local.repository.impl.NewsLocalRepositoryImpl
import com.example.newsproject.datasource.remote.api.NewsApi
import com.example.newsproject.datasource.remote.repository.INewsRemoteRepository
import com.example.newsproject.datasource.remote.repository.impl.NewsRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(api: NewsApi): INewsRemoteRepository = NewsRemoteRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideTopNewsLocalRepository(
        topNewsDao: TopNewsDao, breakingNewsDao: BreakingNewsDao
    ): ITopNewsLocalRepository = NewsLocalRepositoryImpl(topNewsDao, breakingNewsDao)

    @Provides
    @Singleton
    fun provideBreakingNewsLocalRepository(
        topNewsDao: TopNewsDao, breakingNewsDao: BreakingNewsDao
    ): IBreakingNewsLocalRepository = NewsLocalRepositoryImpl(topNewsDao, breakingNewsDao)
}