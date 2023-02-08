package com.example.newsproject.di

import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.mapper.impl.NewsMapper
import com.example.newsproject.model.BreakingNewsItemModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @NewsModuleMapper
    @Provides
    @Singleton
    fun provideNewsMapper(): ISingleMapper<ArticleItemResponse, BreakingNewsItemModel> {
        return NewsMapper()
    }

}