package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.CategoryNewsItemModel
import com.example.newsproject.util.toDateFormatted

class BreakingNewsMapper : ISingleMapper<ArticleItemResponse, CategoryNewsItemModel> {
    override fun invoke(value: ArticleItemResponse): CategoryNewsItemModel = CategoryNewsItemModel(
        name = value.source?.name,
        title = value.title,
        imageUrl = value.urlToImage,
        publishedAt = value.publishedAt?.toDateFormatted(),
        content = value.description,
    )
}