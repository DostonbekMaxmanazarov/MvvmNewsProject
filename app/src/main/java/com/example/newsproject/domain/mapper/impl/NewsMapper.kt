package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.NewsModel

class NewsMapper : ISingleMapper<ArticleItemResponse, NewsModel> {
    override fun invoke(value: ArticleItemResponse): NewsModel = NewsModel(
        name = value.source?.name,
        title = value.title,
        imageUrl = value.urlToImage,
        publishedAt = value.publishedAt,
        content = value.content,
    )
}