package com.example.newsproject.domain.usecase

import com.example.newsproject.model.SearchNewsModel

interface ISearchingAddBookmarkUseCase {
    suspend operator fun invoke(
        categoryNewsItemModel: SearchNewsModel
    )
}