package com.example.newsproject.domain.usecase

import com.example.newsproject.model.TopStoriesNewsModel
import com.example.newsproject.datasource.utils.ResultEvent

interface ITopStoriesUseCase {
    suspend operator fun invoke(isLoadingLocal:Boolean): ResultEvent<TopStoriesNewsModel>
}