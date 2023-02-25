package com.example.newsproject.di.module

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.datasource.local.repository.IBookmarkLocalRepository
import com.example.newsproject.datasource.local.repository.ICategoryLocalRepository
import com.example.newsproject.datasource.remote.repository.ICategoryRemoteRepository
import com.example.newsproject.datasource.remote.repository.ISearchRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticleDataResponse
import com.example.newsproject.di.qualifier.*
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.usecase.ICategoryAddBookmarkUseCase
import com.example.newsproject.domain.usecase.ICategoryNewsUseCase
import com.example.newsproject.domain.usecase.ISearchNewsUseCase
import com.example.newsproject.domain.usecase.ISearchingAddBookmarkUseCase
import com.example.newsproject.domain.usecase.impl.CategoryAddBookmarkUseCaseImpl
import com.example.newsproject.domain.usecase.impl.CategoryNewsUseCaseImpl
import com.example.newsproject.domain.usecase.impl.SearchNewsUseCaseImpl
import com.example.newsproject.domain.usecase.impl.SearchingAddBookmarkUseCaseImpl
import com.example.newsproject.model.CategoryNewsItemModel
import com.example.newsproject.model.SearchNewsItemModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideCategoryNewsUseCase(
        remoteRepository: ICategoryRemoteRepository,
        localRepository: ICategoryLocalRepository,
        @CategoryNewsModuleMapper categoryNewsRemoteMapper: ISingleMapper<ArticleDataResponse, CategoryNewsItemModel>,
        @CategoryNewsLocalModuleMapper categoryNewsLocalMapper: ISingleMapper<ArticleDataResponse, CategoryNewsEntity>,
        @ParseToCategoryNewsModuleMapper parseToCategoryNewsLocalMapper: ISingleMapper<CategoryNewsEntity, CategoryNewsItemModel>
    ): ICategoryNewsUseCase = CategoryNewsUseCaseImpl(
        remoteRepository,
        localRepository,
        categoryNewsRemoteMapper,
        categoryNewsLocalMapper,
        parseToCategoryNewsLocalMapper
    )

    @Provides
    @Singleton
    fun provideSearchNewsUseCase(
        remoteRepository: ISearchRemoteRepository,
        @SearchNewsModuleMapper searchNewsRemoteMapper: ISingleMapper<ArticleDataResponse, SearchNewsItemModel>,
    ): ISearchNewsUseCase = SearchNewsUseCaseImpl(
        remoteRepository,
        searchNewsRemoteMapper
    )

    @Provides
    @Singleton
    fun provideSearchAddBookmarkUseCase(
        localRepository: IBookmarkLocalRepository,
        @SearchingBookmarkLocalModuleMapper searchBookmarkMapper: ISingleMapper<SearchNewsItemModel, BookmarkNewsEntity>,
    ): ISearchingAddBookmarkUseCase = SearchingAddBookmarkUseCaseImpl(
        localRepository,
        searchBookmarkMapper
    )

    @Provides
    @Singleton
    fun provideCategoryAddBookmarkUseCase(
        localRepository: IBookmarkLocalRepository,
        @CategoryBookmarkLocalModuleMapper searchBookmarkMapper: ISingleMapper<CategoryNewsItemModel, BookmarkNewsEntity>,
    ): ICategoryAddBookmarkUseCase = CategoryAddBookmarkUseCaseImpl(
        localRepository,
        searchBookmarkMapper
    )
}