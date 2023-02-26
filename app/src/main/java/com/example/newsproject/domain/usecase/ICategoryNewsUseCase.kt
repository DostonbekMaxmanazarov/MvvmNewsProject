package com.example.newsproject.domain.usecase

import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.model.CategoryNewsModel

interface ICategoryNewsUseCase {
    suspend operator fun invoke(
        isLoadingLocal: Boolean, category: String
    ): ResultEvent<List<CategoryNewsModel>>
}