package com.example.newsproject.di.module

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.datasource.remote.response.ArticleDataResponse
import com.example.newsproject.di.qualifier.*
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.mapper.impl.*
import com.example.newsproject.model.BookmarkNewsModel
import com.example.newsproject.model.CategoryNewsModel
import com.example.newsproject.model.SearchNewsModel
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
    fun provideBreakingNewsMapper(): ISingleMapper<ArticleDataResponse, CategoryNewsModel> {
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
    fun provideParseToBreakingNewsMapper(): ISingleMapper<CategoryNewsEntity, CategoryNewsModel> {
        return ParseToCategoryNewsMapper()
    }

    @SearchNewsModuleMapper
    @Provides
    @Singleton
    fun provideSearchNewsMapper(): ISingleMapper<ArticleDataResponse, SearchNewsModel> {
        return SearchNewsMapper()
    }

    @CategoryBookmarkLocalModuleMapper
    @Provides
    @Singleton
    fun provideCategoryBookmarkNewsLocalMapper(): ISingleMapper<CategoryNewsModel, BookmarkNewsEntity> {
        return CategoryBookmarkNewsLocalMapper()
    }

    @SearchingBookmarkLocalModuleMapper
    @Provides
    @Singleton
    fun provideSearchingBookmarkNewsLocalMapper(): ISingleMapper<SearchNewsModel, BookmarkNewsEntity> {
        return SearchingBookmarkNewsLocalMapper()
    }

    @BookmarkModuleMapper
    @Provides
    @Singleton
    fun provideBookmarkNewsMapper(): ISingleMapper<BookmarkNewsEntity, BookmarkNewsModel> {
        return BookmarkNewsModelMapper()
    }
}