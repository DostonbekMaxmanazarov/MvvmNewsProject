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
import com.example.newsproject.domain.usecase.*
import com.example.newsproject.domain.usecase.impl.*
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
class UseCaseModule {

    @Provides
    @Singleton
    fun provideCategoryNewsUseCase(
        remoteRepository: ICategoryRemoteRepository,
        localRepository: ICategoryLocalRepository,
        @CategoryNewsModuleMapper categoryNewsRemoteMapper: ISingleMapper<ArticleDataResponse, CategoryNewsModel>,
        @CategoryNewsLocalModuleMapper categoryNewsLocalMapper: ISingleMapper<ArticleDataResponse, CategoryNewsEntity>,
    ): ICategoryNewsUseCase = CategoryNewsUseCaseImpl(
        remoteRepository, localRepository, categoryNewsRemoteMapper, categoryNewsLocalMapper
    )

    @Provides
    @Singleton
    fun provideSearchNewsUseCase(
        remoteRepository: ISearchRemoteRepository,
        @SearchNewsModuleMapper searchNewsRemoteMapper: ISingleMapper<ArticleDataResponse, SearchNewsModel>,
    ): ISearchNewsUseCase = SearchNewsUseCaseImpl(
        remoteRepository, searchNewsRemoteMapper
    )

    @Provides
    @Singleton
    fun provideSearchAddBookmarkUseCase(
        localRepository: IBookmarkLocalRepository,
        @SearchingBookmarkLocalModuleMapper searchBookmarkMapper: ISingleMapper<SearchNewsModel, BookmarkNewsEntity>,
    ): ISearchingAddBookmarkUseCase = SearchingAddBookmarkUseCaseImpl(
        localRepository, searchBookmarkMapper
    )

    @Provides
    @Singleton
    fun provideCategoryAddBookmarkUseCase(
        localRepository: IBookmarkLocalRepository,
        @CategoryBookmarkLocalModuleMapper searchBookmarkMapper: ISingleMapper<CategoryNewsModel, BookmarkNewsEntity>,
    ): ICategoryAddBookmarkUseCase = CategoryAddBookmarkUseCaseImpl(
        localRepository, searchBookmarkMapper
    )

    @Provides
    @Singleton
    fun provideGetAllBookmarkNewsUseCase(
        localRepository: IBookmarkLocalRepository,
        @BookmarkModuleMapper bookmarkNewsMapper: ISingleMapper<BookmarkNewsEntity, BookmarkNewsModel>,
    ): IGetAllBookmarkNewsUseCase = GetAllBookmarkNewsUseCaseImpl(
        localRepository, bookmarkNewsMapper
    )
}