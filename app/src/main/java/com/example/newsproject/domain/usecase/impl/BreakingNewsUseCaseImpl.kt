package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.remote.repository.INewsRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.di.NewsModuleMapper
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.usecase.IBreakingNewsUseCase
import com.example.newsproject.model.BreakingNewsItemModel
import com.example.newsproject.model.BreakingNewsModel
import com.example.newsproject.util.ResultEvent
import javax.inject.Inject

class BreakingNewsUseCaseImpl @Inject constructor(
    private val remoteRepo: INewsRemoteRepository,
   @NewsModuleMapper private val newsMapper: ISingleMapper<ArticleItemResponse, BreakingNewsItemModel>
) : IBreakingNewsUseCase {

    override suspend fun invoke(): ResultEvent<BreakingNewsModel> {
        return try {
            ResultEvent.Loading
            val response = remoteRepo.getTopStories()
            val result = response.articles

            if (result != null) {
                val newsModel = result.map { newsMapper.invoke(it) }
                ResultEvent.Success(BreakingNewsModel(newsModel))
            } else ResultEvent.Error("Articles not found")
        } catch (t: Throwable) {
            ResultEvent.Error(t.message)
        }
    }
}