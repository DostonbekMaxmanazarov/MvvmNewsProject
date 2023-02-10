package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.local.entity.BreakingNewsEntity
import com.example.newsproject.datasource.local.repository.IBreakingNewsLocalRepository
import com.example.newsproject.datasource.local.repository.ITopNewsLocalRepository
import com.example.newsproject.datasource.remote.repository.INewsRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.di.qualifier.BreakingNewsModuleMapper
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.usecase.IBreakingNewsUseCase
import com.example.newsproject.model.BreakingNewsItemModel
import com.example.newsproject.model.BreakingNewsModel
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.di.qualifier.BreakingNewsLocalModuleMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BreakingNewsUseCaseImpl @Inject constructor(
    private val remoteRepo: INewsRemoteRepository,
    private val localRepo: IBreakingNewsLocalRepository,
    @BreakingNewsModuleMapper private val newsRemoteMapper: ISingleMapper<ArticleItemResponse, BreakingNewsItemModel>,
    @BreakingNewsLocalModuleMapper private val newsLocalMapper: ISingleMapper<ArticleItemResponse, BreakingNewsEntity>
) : IBreakingNewsUseCase {

    override suspend fun invoke(isSuccessNetwork: Boolean): ResultEvent<BreakingNewsModel> {
        return try {
            if (!isSuccessNetwork) {
                val isNotEmpty = localRepo.getAllBreakingNews().isNotEmpty()
                if (isNotEmpty) {
                    val result = localRepo.getAllBreakingNews()
                    val newsModel = result.map {  }
                }
            }

            val response = withContext(Dispatchers.IO) { remoteRepo.getTopStories() }
            val result = response.articles

            if (result != null) {
                val newsModel = result.map { newsRemoteMapper.invoke(it) }
                val newsLocalModel = result.map { newsLocalMapper.invoke(it) }
                localRepo.addBreakingNews(newsLocalModel)
                ResultEvent.Success(BreakingNewsModel(newsModel))
            } else ResultEvent.Failure("Articles not found")
        } catch (t: Throwable) {
            ResultEvent.Failure("Connection Error")
        }
    }
}