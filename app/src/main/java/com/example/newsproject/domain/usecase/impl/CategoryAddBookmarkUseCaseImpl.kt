package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.datasource.local.repository.IBookmarkLocalRepository
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.di.qualifier.CategoryBookmarkLocalModuleMapper
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.usecase.ICategoryAddBookmarkUseCase
import com.example.newsproject.model.CategoryNewsModel

class CategoryAddBookmarkUseCaseImpl(
    private val localRepo: IBookmarkLocalRepository,
    @CategoryBookmarkLocalModuleMapper private val categoryBookmarkMapper: ISingleMapper<CategoryNewsModel, BookmarkNewsEntity>
) : ICategoryAddBookmarkUseCase {
    override suspend fun invoke(categoryNewsItemModel: CategoryNewsModel): ResultEvent<Boolean> {
        val bookmarkNews = categoryBookmarkMapper(categoryNewsItemModel)
        val id = localRepo.addBookmarkNews(bookmarkNews)
        return if (id > 0) ResultEvent.Success(true)
        else ResultEvent.Failure("bookmark news not added")
    }
}