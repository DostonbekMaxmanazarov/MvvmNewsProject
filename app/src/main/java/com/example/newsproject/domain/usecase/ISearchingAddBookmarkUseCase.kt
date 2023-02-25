package com.example.newsproject.domain.usecase

import com.example.newsproject.model.SearchNewsItemModel

interface ISearchingAddBookmarkUseCase {
    suspend operator fun invoke(
        categoryNewsItemModel: SearchNewsItemModel
    )
}