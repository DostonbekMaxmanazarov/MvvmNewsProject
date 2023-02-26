package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.datasource.local.repository.IBookmarkLocalRepository
import com.example.newsproject.di.qualifier.SearchingBookmarkLocalModuleMapper
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.usecase.ISearchingAddBookmarkUseCase
import com.example.newsproject.model.SearchNewsModel
import javax.inject.Inject

class SearchingAddBookmarkUseCaseImpl @Inject constructor(
    private val localRepo: IBookmarkLocalRepository,
    @SearchingBookmarkLocalModuleMapper private val searchingBookmarkMapper: ISingleMapper<SearchNewsModel, BookmarkNewsEntity>
) : ISearchingAddBookmarkUseCase {
    override suspend fun invoke(categoryNewsItemModel: SearchNewsModel) {
        val bookmarkNews = searchingBookmarkMapper(categoryNewsItemModel)
        localRepo.addBookmarkNews(bookmarkNews)
    }
}