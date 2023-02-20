package com.example.newsproject.di.module

import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.datasource.local.entity.TopNewsEntity
import com.example.newsproject.datasource.local.repository.ICategoryNewsLocalRepository
import com.example.newsproject.datasource.local.repository.ITopNewsLocalRepository
import com.example.newsproject.datasource.remote.repository.INewsRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.di.qualifier.*
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.usecase.ICategoryNewsUseCase
import com.example.newsproject.domain.usecase.ITopStoriesUseCase
import com.example.newsproject.domain.usecase.impl.CategoryNewsUseCaseImpl
import com.example.newsproject.model.CategoryNewsItemModel
import com.example.newsproject.model.TopStoriesNewsItemModel
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
    fun provideBreakingNewsUseCase(
        remoteRepository: INewsRemoteRepository,
        localRepository: ICategoryNewsLocalRepository,
        @BreakingNewsModuleMapper breakingNewsRemoteMapper: ISingleMapper<ArticleItemResponse, CategoryNewsItemModel>,
        @BreakingNewsLocalModuleMapper breakingNewsLocalMapper: ISingleMapper<ArticleItemResponse, CategoryNewsEntity>,
        @ParseToBreakingNewsModuleMapper parseToBreakingNewsLocalMapper: ISingleMapper<CategoryNewsEntity, CategoryNewsItemModel>
    ): ICategoryNewsUseCase = CategoryNewsUseCaseImpl(
        remoteRepository,
        localRepository,
        breakingNewsRemoteMapper,
        breakingNewsLocalMapper,
        parseToBreakingNewsLocalMapper
    )

    @Provides
    @Singleton
    fun provideTopNewsUseCase(
        repository: INewsRemoteRepository,
        localRepository: ITopNewsLocalRepository,
        @TopNewsModuleMapper mapper: ISingleMapper<ArticleItemResponse, TopStoriesNewsItemModel>,
        @TopNewsLocalModuleMapper breakingNewsLocalMapper: ISingleMapper<ArticleItemResponse, TopNewsEntity>,
        @ParseToTopNewsModuleMapper parseToBreakingNewsLocalMapper: ISingleMapper<TopNewsEntity, TopStoriesNewsItemModel>
    ): ITopStoriesUseCase = TopStoriesUseCaseImpl(
        repository,
        localRepository,
        mapper,
        breakingNewsLocalMapper,
        parseToBreakingNewsLocalMapper
    )

}