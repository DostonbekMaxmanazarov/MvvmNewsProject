package com.example.newsproject.domain.usecase

import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.model.CategoryNewsModel


interface ICategoryAddBookmarkUseCase {
    suspend operator fun invoke(
        categoryNewsItemModel: CategoryNewsModel
    ): ResultEvent<Boolean>
}