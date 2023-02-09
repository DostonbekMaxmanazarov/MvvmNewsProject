package com.example.newsproject.domain.usecase

import com.example.newsproject.model.TopStoriesNewsModel
import com.example.newsproject.util.ResultEvent

interface ITopStoriesUseCase {
    suspend operator fun invoke(): ResultEvent<TopStoriesNewsModel>
}