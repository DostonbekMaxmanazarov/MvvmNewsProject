package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.remote.repository.INewsRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.usecase.IBreakingNewsUseCase
import com.example.newsproject.model.NewsModel
import com.example.newsproject.util.ResultEvent

class BreakingNewsUseCaseImpl(
    private val remoteRepo: INewsRemoteRepository,
    private val newsMapper: ISingleMapper<ArticleItemResponse, NewsModel>
) : IBreakingNewsUseCase {

    override suspend fun invoke(): ResultEvent<List<NewsModel>> {
        return try {
            ResultEvent.Loading
            val response = remoteRepo.getTopStories()
            val result = response.body?.articles
            val statue = response.status

            if (statue == "ok" && result != null) {
                val newsModel = result.map { newsMapper.invoke(it) }
                ResultEvent.Success(newsModel)
            } else ResultEvent.Error("Articles not found")
        } catch (t: Throwable) {
            ResultEvent.Error(t.message)
        }
    }
}