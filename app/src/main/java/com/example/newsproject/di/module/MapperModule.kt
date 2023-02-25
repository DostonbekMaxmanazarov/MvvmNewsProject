package com.example.newsproject.di.module

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.datasource.remote.response.ArticleDataResponse
import com.example.newsproject.di.qualifier.*
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.mapper.impl.*
import com.example.newsproject.model.CategoryNewsItemModel
import com.example.newsproject.model.SearchNewsItemModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @CategoryNewsModuleMapper
    @Provides
    @Singleton
    fun provideBreakingNewsMapper(): ISingleMapper<ArticleDataResponse, CategoryNewsItemModel> {
        return CategoryNewsMapper()
    }

    @CategoryNewsLocalModuleMapper
    @Provides
    @Singleton
    fun provideBreakingNewsLocalMapper(): ISingleMapper<ArticleDataResponse, CategoryNewsEntity> {
        return CategoryNewsLocalMapper()
    }

    @ParseToCategoryNewsModuleMapper
    @Provides
    @Singleton
    fun provideParseToBreakingNewsMapper(): ISingleMapper<CategoryNewsEntity, CategoryNewsItemModel> {
        return ParseToCategoryNewsMapper()
    }

    @SearchNewsModuleMapper
    @Provides
    @Singleton
    fun provideSearchNewsMapper(): ISingleMapper<ArticleDataResponse, SearchNewsItemModel> {
        return SearchNewsMapper()
    }

    @CategoryBookmarkLocalModuleMapper
    @Provides
    @Singleton
    fun provideCategoryBookmarkNewsLocalMapper(): ISingleMapper<CategoryNewsItemModel, BookmarkNewsEntity> {
        return CategoryBookmarkNewsLocalMapper()
    }

    @SearchingBookmarkLocalModuleMapper
    @Provides
    @Singleton
    fun provideSearchingBookmarkNewsLocalMapper(): ISingleMapper<SearchNewsItemModel, BookmarkNewsEntity> {
        return SearchingBookmarkNewsLocalMapper()
    }
}