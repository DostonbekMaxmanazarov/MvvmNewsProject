package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.datasource.local.repository.ICategoryNewsLocalRepository
import com.example.newsproject.datasource.remote.repository.INewsRemoteRepository
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.di.qualifier.BreakingNewsModuleMapper
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.usecase.ICategoryNewsUseCase
import com.example.newsproject.model.CategoryNewsItemModel
import com.example.newsproject.model.CategoryNewsModel
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.di.qualifier.BreakingNewsLocalModuleMapper
import com.example.newsproject.di.qualifier.ParseToBreakingNewsModuleMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryNewsUseCaseImpl @Inject constructor(
    private val remoteRepo: INewsRemoteRepository,
    private val localRepo: ICategoryNewsLocalRepository,
    @BreakingNewsModuleMapper private val newsRemoteMapper: ISingleMapper<ArticleItemResponse, CategoryNewsItemModel>,
    @BreakingNewsLocalModuleMapper private val newsLocalMapper: ISingleMapper<ArticleItemResponse, CategoryNewsEntity>,
    @ParseToBreakingNewsModuleMapper private val parseToCategoryNewsMapper: ISingleMapper<CategoryNewsEntity, CategoryNewsItemModel>
) : ICategoryNewsUseCase {

    override suspend fun invoke(isLoadingLocal: Boolean, category: String): ResultEvent<CategoryNewsModel> {
        return try { // if the user cannot connect to the Internet, it downloads the data from the database
            if (isLoadingLocal) {
                val isNotEmpty = localRepo.getAllCategoryNews().isNotEmpty()
                if (isNotEmpty) {
                    val result = withContext(Dispatchers.IO) { localRepo.getAllCategoryNews() }
                    val newsModel = result.map { parseToCategoryNewsMapper.invoke(it) }
                    ResultEvent.Success(CategoryNewsModel(newsModel))
                } else ResultEvent.Error("Articles is Empty")
            } else { // when the internet works, data is downloaded from the internet
                val response = withContext(Dispatchers.IO) { remoteRepo.getCategoryNews(category) }
                val result = response.articles
                if (result != null) {
                    localRepo.deleteAllBreakingNews()
                    val newsModel = result.map { newsRemoteMapper.invoke(it) }
                    val newsLocalModel = result.map { newsLocalMapper.invoke(it) }
                    localRepo.addCategoryNews(newsLocalModel)
                    ResultEvent.Success(CategoryNewsModel(newsModel))
                } else ResultEvent.Failure("Articles not found")
            }
        } catch (t: Throwable) {
            ResultEvent.Failure("Connection Error")
        }
    }
}