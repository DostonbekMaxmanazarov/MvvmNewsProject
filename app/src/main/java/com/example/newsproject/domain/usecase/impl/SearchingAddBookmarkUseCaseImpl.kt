package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.datasource.local.repository.IBookmarkLocalRepository
import com.example.newsproject.di.qualifier.SearchingBookmarkLocalModuleMapper
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.domain.mapper.impl.SearchingBookmarkNewsLocalMapper
import com.example.newsproject.domain.usecase.ISearchingAddBookmarkUseCase
import com.example.newsproject.model.SearchNewsItemModel
import javax.inject.Inject

class SearchingAddBookmarkUseCaseImpl @Inject constructor(
    private val localRepo: IBookmarkLocalRepository,
    @SearchingBookmarkLocalModuleMapper private val searchingBookmarkMapper: ISingleMapper<SearchNewsItemModel, BookmarkNewsEntity>
) : ISearchingAddBookmarkUseCase {
    override suspend fun invoke(categoryNewsItemModel: SearchNewsItemModel) {
        val bookmarkNews = searchingBookmarkMapper(categoryNewsItemModel)
        localRepo.addBookmarkNews(bookmarkNews)
    }
}