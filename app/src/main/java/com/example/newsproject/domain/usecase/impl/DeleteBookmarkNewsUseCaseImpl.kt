package com.example.newsproject.domain.usecase.impl

import com.example.newsproject.datasource.local.repository.IBookmarkLocalRepository
import com.example.newsproject.domain.usecase.IDeleteBookmarkNewsUseCase

class DeleteBookmarkNewsUseCaseImpl(
    private val localRepository: IBookmarkLocalRepository
) :IDeleteBookmarkNewsUseCase{
    override suspend fun invoke(id: Int) {
        localRepository.deleteBookmark(id)
    }
}