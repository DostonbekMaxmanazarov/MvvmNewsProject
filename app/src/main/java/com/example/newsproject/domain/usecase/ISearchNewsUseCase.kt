package com.example.newsproject.domain.usecase

import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.model.SearchNewsItemModel

interface ISearchNewsUseCase {
    suspend operator fun invoke( searchText: String
    ): ResultEvent<List<SearchNewsItemModel>>
}