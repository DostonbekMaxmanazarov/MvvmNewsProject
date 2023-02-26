package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.datasource.local.repository.IBookmarkLocalRepository
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.di.qualifier.SearchNewsModuleMapper
import com.example.newsproject.domain.usecase.IGetAllBookmarkNewsUseCase
import com.example.newsproject.model.BookmarkNewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllBookmarkNewsUseCaseImpl @Inject constructor(
    private val localRepo: IBookmarkLocalRepository,
    @SearchNewsModuleMapper private val bookmarkNewsMapper: ISingleMapper<BookmarkNewsEntity, BookmarkNewsModel>,
) : IGetAllBookmarkNewsUseCase {

    override suspend fun invoke(): ResultEvent<List<BookmarkNewsModel>> {
        return try {
            val response = withContext(Dispatchers.IO) { localRepo.getAllBookmarkNews() }
            if (response.isNotEmpty()) {
                val newsModel = response.map { bookmarkNewsMapper(it) }
                ResultEvent.Success(newsModel)
            } else ResultEvent.Failure("Data is empty")
        } catch (t: Throwable) {
            ResultEvent.Failure("Connection Error")
        } finally {
            ResultEvent.Loading(false)
        }
    }
}