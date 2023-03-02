package com.example.newsproject.domain.usecase

import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.model.BookmarkNewsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface IGetAllBookmarkNewsUseCase {
    operator fun invoke(): Flow<Unit>
    val bookmarksSharedFlow: SharedFlow<ResultEvent<List<BookmarkNewsModel>>>

}