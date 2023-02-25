package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.datasource.local.repository.IBookmarkLocalRepository
import com.example.newsproject.di.qualifier.CategoryBookmarkLocalModuleMapper
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.mapper.impl.CategoryBookmarkNewsLocalMapper
import com.example.newsproject.domain.usecase.ICategoryAddBookmarkUseCase
import com.example.newsproject.model.CategoryNewsItemModel

class CategoryAddBookmarkUseCaseImpl(
    private val localRepo: IBookmarkLocalRepository,
    @CategoryBookmarkLocalModuleMapper private val categoryBookmarkMapper: ISingleMapper<CategoryNewsItemModel, BookmarkNewsEntity>
) : ICategoryAddBookmarkUseCase {
    override suspend fun invoke(categoryNewsItemModel: CategoryNewsItemModel) {
        val bookmarkNews = categoryBookmarkMapper(categoryNewsItemModel)
        localRepo.addBookmarkNews(bookmarkNews)
    }
}