package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.BreakingNewsItemModel

class NewsMapper : ISingleMapper<ArticleItemResponse, BreakingNewsItemModel> {
    override fun invoke(value: ArticleItemResponse): BreakingNewsItemModel = BreakingNewsItemModel(
        name = value.source?.name,
        title = value.title,
        imageUrl = value.urlToImage,
        publishedAt = value.publishedAt,
        content = value.content,
    )
}