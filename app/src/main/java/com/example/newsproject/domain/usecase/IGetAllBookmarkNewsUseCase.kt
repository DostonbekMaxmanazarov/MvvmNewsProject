package com.example.newsproject.domain.usecase

import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.model.BookmarkNewsModel

interface IGetAllBookmarkNewsUseCase {
    suspend operator fun invoke(): ResultEvent<List<BookmarkNewsModel>>
}