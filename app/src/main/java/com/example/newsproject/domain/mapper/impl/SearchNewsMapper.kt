package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.remote.response.ArticleDataResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.SearchNewsItemModel
import com.example.newsproject.util.toDateFormatted

class SearchNewsMapper : ISingleMapper<ArticleDataResponse, SearchNewsItemModel> {
    override fun invoke(value: ArticleDataResponse): SearchNewsItemModel = SearchNewsItemModel(
        name = value.source?.name,
        title = value.title,
        imageUrl = value.urlToImage,
        publishedAt = value.publishedAt?.toDateFormatted()
    )
}