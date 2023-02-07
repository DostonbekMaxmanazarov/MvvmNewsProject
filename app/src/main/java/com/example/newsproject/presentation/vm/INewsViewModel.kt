package com.example.newsproject.presentation.vm

import com.example.newsproject.model.NewsModel
import com.example.newsproject.util.ResultEvent
import kotlinx.coroutines.flow.StateFlow

interface INewsViewModel {
    val newsStateFlow: StateFlow<ResultEvent<List<NewsModel>>>
}