package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.local.entity.TopNewsEntity
import com.example.newsproject.datasource.local.repository.ITopNewsLocalRepository
import com.example.newsproject.datasource.remote.repository.INewsRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.usecase.ITopStoriesUseCase
import com.example.newsproject.model.TopStoriesNewsItemModel
import com.example.newsproject.model.TopStoriesNewsModel
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.di.qualifier.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopStoriesUseCaseImpl @Inject constructor(
    private val remoteRepo: INewsRemoteRepository,
    private val localRepo: ITopNewsLocalRepository,
    @TopNewsModuleMapper private val newsMapper: ISingleMapper<ArticleItemResponse, TopStoriesNewsItemModel>,
    @TopNewsLocalModuleMapper private val newsLocalMapper: ISingleMapper<ArticleItemResponse, TopNewsEntity>,
    @ParseToTopNewsModuleMapper private val parseToBreakingNewsMapper: ISingleMapper<TopNewsEntity, TopStoriesNewsItemModel>
) : ITopStoriesUseCase {

    override suspend fun invoke(isLoadingLocal: Boolean): ResultEvent<TopStoriesNewsModel> {
        return try { // if the user cannot connect to the Internet, it downloads the data from the database
            if (isLoadingLocal) {
                val isNotEmpty = localRepo.getAllTopNews().isNotEmpty()
                if (isNotEmpty) {
                    val result = withContext(Dispatchers.IO) { localRepo.getAllTopNews() }
                    val newsModel = result.map { parseToBreakingNewsMapper.invoke(it) }
                    ResultEvent.Success(TopStoriesNewsModel(newsModel))
                } else ResultEvent.Error("Articles is Empty")
            } else { // when the internet works, data is downloaded from the internet
                val response = withContext(Dispatchers.IO) { remoteRepo.getTopStories() }
                val result = response.articles
                if (result != null) {
                    localRepo.deleteAllTopNews()
                    val newsModel = result.map { newsMapper.invoke(it) }
                    val newsLocalModel = result.map { newsLocalMapper.invoke(it) }
                    localRepo.addTopNews(newsLocalModel)
                    ResultEvent.Success(TopStoriesNewsModel(newsModel))
                } else ResultEvent.Failure("Articles not found")
            }
        } catch (t: Throwable) {
            ResultEvent.Failure("Connection Error")
        }
    }
}