package com.example.newsproject.di.module

import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.datasource.local.entity.TopNewsEntity
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.di.qualifier.*
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.mapper.impl.*
import com.example.newsproject.model.CategoryNewsItemModel
import com.example.newsproject.model.TopStoriesNewsItemModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @BreakingNewsModuleMapper
    @Provides
    @Singleton
    fun provideBreakingNewsMapper(): ISingleMapper<ArticleItemResponse, CategoryNewsItemModel> {
        return BreakingNewsMapper()
    }

    @BreakingNewsLocalModuleMapper
    @Provides
    @Singleton
    fun provideBreakingNewsLocalMapper(): ISingleMapper<ArticleItemResponse, CategoryNewsEntity> {
        return BreakingNewsLocalMapper()
    }

    @ParseToBreakingNewsModuleMapper
    @Provides
    @Singleton
    fun provideParseToBreakingNewsMapper(): ISingleMapper<CategoryNewsEntity, CategoryNewsItemModel> {
        return ParseToBreakingNewsMapper()
    }

    @TopNewsModuleMapper
    @Provides
    @Singleton
    fun provideTopNewsMapper(): ISingleMapper<ArticleItemResponse, TopStoriesNewsItemModel> {
        return TopNewsMapper()
    }

    @TopNewsLocalModuleMapper
    @Provides
    @Singleton
    fun provideTopNewsLocalMapper(): ISingleMapper<ArticleItemResponse, TopNewsEntity> {
        return TopNewsLocalMapper()
    }

    @ParseToTopNewsModuleMapper
    @Provides
    @Singleton
    fun provideParseToTopNewsMapper(): ISingleMapper<TopNewsEntity, TopStoriesNewsItemModel> {
        return ParseToTopNewsMapper()
    }

}