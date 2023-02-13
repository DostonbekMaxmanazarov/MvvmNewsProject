package com.example.newsproject.di.module

import com.example.newsproject.datasource.local.entity.BreakingNewsEntity
import com.example.newsproject.datasource.local.entity.TopNewsEntity
import com.example.newsproject.datasource.local.repository.IBreakingNewsLocalRepository
import com.example.newsproject.datasource.local.repository.ITopNewsLocalRepository
import com.example.newsproject.datasource.remote.repository.INewsRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.di.qualifier.*
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.usecase.IBreakingNewsUseCase
import com.example.newsproject.domain.usecase.ITopStoriesUseCase
import com.example.newsproject.domain.usecase.impl.BreakingNewsUseCaseImpl
import com.example.newsproject.domain.usecase.impl.TopStoriesUseCaseImpl
import com.example.newsproject.model.BreakingNewsItemModel
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
        localRepository: IBreakingNewsLocalRepository,
        @BreakingNewsModuleMapper breakingNewsRemoteMapper: ISingleMapper<ArticleItemResponse, BreakingNewsItemModel>,
        @BreakingNewsLocalModuleMapper breakingNewsLocalMapper: ISingleMapper<ArticleItemResponse, BreakingNewsEntity>,
        @ParseToBreakingNewsModuleMapper parseToBreakingNewsLocalMapper: ISingleMapper<BreakingNewsEntity, BreakingNewsItemModel>
    ): IBreakingNewsUseCase = BreakingNewsUseCaseImpl(
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