package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.datasource.local.repository.ICategoryLocalRepository
import com.example.newsproject.datasource.remote.repository.ICategoryRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticleDataResponse
import com.example.newsproject.di.qualifier.CategoryNewsModuleMapper
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.usecase.ICategoryNewsUseCase
import com.example.newsproject.model.CategoryNewsItemModel
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.di.qualifier.CategoryNewsLocalModuleMapper
import com.example.newsproject.di.qualifier.ParseToCategoryNewsModuleMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryNewsUseCaseImpl @Inject constructor(
    private val remoteRepo: ICategoryRemoteRepository,
    private val localRepo: ICategoryLocalRepository,
    @CategoryNewsModuleMapper private val newsRemoteMapper: ISingleMapper<ArticleDataResponse, CategoryNewsItemModel>,
    @CategoryNewsLocalModuleMapper private val newsLocalMapper: ISingleMapper<ArticleDataResponse, CategoryNewsEntity>,
    @ParseToCategoryNewsModuleMapper private val parseToCategoryNewsMapper: ISingleMapper<CategoryNewsEntity, CategoryNewsItemModel>
) : ICategoryNewsUseCase {

    override suspend fun invoke(
        isLoadingLocal: Boolean, category: String
    ): ResultEvent<List<CategoryNewsItemModel>> {
        ResultEvent.Loading(true)
        return try { // if the user cannot connect to the Internet, it downloads the data from the database
            if (isLoadingLocal) {
                val isNotEmpty = localRepo.getAllCategoryNews().isNotEmpty()
                if (isNotEmpty) {
                    val result = withContext(Dispatchers.IO) { localRepo.getAllCategoryNews() }
                    val newsModel = result.map { parseToCategoryNewsMapper(it) }
                    ResultEvent.Success(newsModel)
                } else ResultEvent.Error("Articles is Empty")
            } else {
                // when the internet works, data is downloaded from the internet
                val response = withContext(Dispatchers.IO) { remoteRepo.getCategoryNews(category) }
                val result = response.articles
                if (result != null) {
                    localRepo.deleteAllBreakingNews()
                    val newsModel = result.map { newsRemoteMapper(it) }
                    val newsLocalModel = result.map { newsLocalMapper(it) }
                    localRepo.addCategoryNews(newsLocalModel)
                    ResultEvent.Success(newsModel)
                } else ResultEvent.Failure("Articles not found")
            }
        } catch (t: Throwable) {
            ResultEvent.Failure("Connection Error")
        } finally {
            ResultEvent.Loading(false)
        }
    }
}