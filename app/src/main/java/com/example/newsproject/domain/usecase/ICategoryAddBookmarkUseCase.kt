package com.example.newsproject.domain.usecase

import com.example.newsproject.model.CategoryNewsModel


interface ICategoryAddBookmarkUseCase {
    suspend operator fun invoke(
        categoryNewsItemModel: CategoryNewsModel
    )
}