package com.example.newsproject.domain.usecase

import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.model.CategoryNewsItemModel

interface ICategoryNewsUseCase {
    suspend operator fun invoke(
        isLoadingLocal: Boolean, category: String
    ): ResultEvent<List<CategoryNewsItemModel>>
}