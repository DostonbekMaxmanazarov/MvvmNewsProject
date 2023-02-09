package com.example.newsproject.di.module

import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.di.qualifier.BreakingNewsModuleMapper
import com.example.newsproject.di.qualifier.TopNewsModuleMapper
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.mapper.impl.BreakingNewsMapper
import com.example.newsproject.domain.mapper.impl.TopNewsMapper
import com.example.newsproject.model.BreakingNewsItemModel
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
    fun provideBreakingNewsMapper(): ISingleMapper<ArticleItemResponse, BreakingNewsItemModel> {
        return BreakingNewsMapper()
    }

    @TopNewsModuleMapper
    @Provides
    @Singleton
    fun provideNewsMapper(): ISingleMapper<ArticleItemResponse, TopStoriesNewsItemModel> {
        return TopNewsMapper()
    }

}