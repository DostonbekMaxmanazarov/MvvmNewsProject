package com.example.newsproject.domain.usecase.impl

import android.content.res.Resources.NotFoundException
import com.example.newsproject.datasource.remote.repository.INewsRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.usecase.ITopStoriesUseCase
import com.example.newsproject.model.NewsModel

class TopStoriesUseCaseImpl constructor(
    private val remoteRepo: INewsRemoteRepository,
    private val newsMapper: ISingleMapper<ArticleItemResponse, NewsModel>
) : ITopStoriesUseCase {

    override suspend fun invoke(): Result<List<NewsModel>> {
        return try {
            val response = remoteRepo.getTopStories()
            val result = response.body?.articles
            val statue = response.status

            if (statue == "ok" && result != null) {
                val newsModel = result.map { newsMapper.invoke(it) }
                Result.success(newsModel)
            } else Result.failure(NotFoundException("Articles not found"))
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}