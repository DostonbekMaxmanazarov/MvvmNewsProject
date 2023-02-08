package com.example.newsproject.domain.usecase

import com.example.newsproject.model.BreakingNewsModel
import com.example.newsproject.util.ResultEvent

interface IBreakingNewsUseCase {
    suspend operator fun invoke(): ResultEvent<BreakingNewsModel>
}