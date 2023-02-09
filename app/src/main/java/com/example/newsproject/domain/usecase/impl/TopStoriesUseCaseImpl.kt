package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.remote.repository.INewsRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.di.qualifier.TopNewsModuleMapper
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.usecase.ITopStoriesUseCase
import com.example.newsproject.model.TopStoriesNewsItemModel
import com.example.newsproject.model.TopStoriesNewsModel
import com.example.newsproject.util.ResultEvent
import javax.inject.Inject

class TopStoriesUseCaseImpl @Inject constructor(
    private val remoteRepo: INewsRemoteRepository,
    @TopNewsModuleMapper private val newsMapper: ISingleMapper<ArticleItemResponse, TopStoriesNewsItemModel>
) : ITopStoriesUseCase {

    override suspend fun invoke(): ResultEvent<TopStoriesNewsModel> {
        return try {
            val response = remoteRepo.getTopStories()
            val result = response.articles

            if (result != null) {
                val newsModel = result.map { newsMapper.invoke(it) }
                ResultEvent.Success(TopStoriesNewsModel(newsModel))
            } else ResultEvent.Error("Articles not found")
        } catch (t: Throwable) {
            ResultEvent.Error(t.message)
        }
    }
}