package com.example.newsproject.domain.usecase

import com.example.newsproject.model.CategoryNewsModel
import com.example.newsproject.datasource.utils.ResultEvent

interface ICategoryNewsUseCase {
    suspend operator fun invoke(
        isLoadingLocal: Boolean, category: String
    ): ResultEvent<CategoryNewsModel>
}