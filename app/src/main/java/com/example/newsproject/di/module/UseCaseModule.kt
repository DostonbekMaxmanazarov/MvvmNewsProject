package com.example.newsproject.di.module

import com.example.newsproject.datasource.local.entity.BreakingNewsEntity
import com.example.newsproject.datasource.local.repository.IBreakingNewsLocalRepository
import com.example.newsproject.datasource.remote.repository.INewsRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.di.qualifier.BreakingNewsLocalModuleMapper
import com.example.newsproject.di.qualifier.BreakingNewsModuleMapper
import com.example.newsproject.di.qualifier.TopNewsModuleMapper
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
    ): IBreakingNewsUseCase = BreakingNewsUseCaseImpl(remoteRepository,localRepository, breakingNewsRemoteMapper,breakingNewsLocalMapper)

    @Provides
    @Singleton
    fun provideTopNewsUseCase(
        repository: INewsRemoteRepository,
        @TopNewsModuleMapper mapper: ISingleMapper<ArticleItemResponse, TopStoriesNewsItemModel>
    ): ITopStoriesUseCase = TopStoriesUseCaseImpl(repository, mapper)

}