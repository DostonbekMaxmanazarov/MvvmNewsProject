package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.remote.repository.ISearchRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticleDataResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.di.qualifier.SearchNewsModuleMapper
import com.example.newsproject.domain.usecase.ISearchNewsUseCase
import com.example.newsproject.model.SearchNewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchNewsUseCaseImpl @Inject constructor(
    private val remoteRepo: ISearchRemoteRepository,
    @SearchNewsModuleMapper private val newsRemoteMapper: ISingleMapper<ArticleDataResponse, SearchNewsModel>,
) : ISearchNewsUseCase {

    override suspend fun invoke(searchText: String): ResultEvent<List<SearchNewsModel>> {
        ResultEvent.Loading(true)
        return try {
            val response = withContext(Dispatchers.IO) { remoteRepo.getSearchNews(searchText) }
            val result = response.articles
            if (result != null) {
                val newsModel = result.map { newsRemoteMapper(it) }
                ResultEvent.Success(newsModel)
            } else ResultEvent.Failure("Articles not found")
        } catch (t: Throwable) {
            ResultEvent.Failure("Connection Error")
        } finally {
            ResultEvent.Loading(false)
        }
    }
}