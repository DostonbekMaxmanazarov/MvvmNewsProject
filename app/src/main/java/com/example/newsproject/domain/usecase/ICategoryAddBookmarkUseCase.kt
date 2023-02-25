package com.example.newsproject.domain.usecase

import com.example.newsproject.model.CategoryNewsItemModel


interface ICategoryAddBookmarkUseCase {
    suspend operator fun invoke(
        categoryNewsItemModel: CategoryNewsItemModel
    )
}