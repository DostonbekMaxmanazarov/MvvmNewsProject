package com.example.newsproject.di

import com.example.newsproject.datasource.remote.repository.INewsRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.usecase.IBreakingNewsUseCase
import com.example.newsproject.domain.usecase.impl.BreakingNewsUseCaseImpl
import com.example.newsproject.model.BreakingNewsItemModel
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
        repository: INewsRemoteRepository,
        @NewsModuleMapper mapper: ISingleMapper<ArticleItemResponse, BreakingNewsItemModel>
    ): IBreakingNewsUseCase = BreakingNewsUseCaseImpl(repository, mapper)

}