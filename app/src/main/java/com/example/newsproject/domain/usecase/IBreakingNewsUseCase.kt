package com.example.newsproject.domain.usecase

import com.example.newsproject.model.BreakingNewsModel
import com.example.newsproject.datasource.utils.ResultEvent

interface IBreakingNewsUseCase {
    suspend operator fun invoke(isLoadingLocal:Boolean): ResultEvent<BreakingNewsModel>
}