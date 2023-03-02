package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.datasource.local.repository.IBookmarkLocalRepository
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.di.qualifier.SearchNewsModuleMapper
import com.example.newsproject.domain.usecase.IGetAllBookmarkNewsUseCase
import com.example.newsproject.model.BookmarkNewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetAllBookmarkNewsUseCaseImpl @Inject constructor(
    private val localRepo: IBookmarkLocalRepository,
    @SearchNewsModuleMapper private val bookmarkNewsMapper: ISingleMapper<BookmarkNewsEntity, BookmarkNewsModel>,
) : IGetAllBookmarkNewsUseCase {

    private val _bookmarksSharedFlow = MutableSharedFlow<ResultEvent<List<BookmarkNewsModel>>>()
    override val bookmarksSharedFlow: SharedFlow<ResultEvent<List<BookmarkNewsModel>>>
        get() = _bookmarksSharedFlow.asSharedFlow()

    override fun invoke(): Flow<Unit> {
        return localRepo.getAllBookmarkNews().map { bookmarkEntities ->
            bookmarkEntities.map {
                bookmarkNewsMapper(it)
            }
        }.onEach {
            _bookmarksSharedFlow.emit(ResultEvent.Success(it))
        }.map {}.flowOn(Dispatchers.IO)

    }
}