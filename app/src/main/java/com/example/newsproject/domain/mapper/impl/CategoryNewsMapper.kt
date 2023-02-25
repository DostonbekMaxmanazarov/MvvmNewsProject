package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.remote.response.ArticleDataResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.CategoryNewsItemModel
import com.example.newsproject.util.toDateFormatted

class CategoryNewsMapper : ISingleMapper<ArticleDataResponse, CategoryNewsItemModel> {
    override fun invoke(value: ArticleDataResponse): CategoryNewsItemModel = CategoryNewsItemModel(
        name = value.source?.name,
        title = value.title,
        imageUrl = value.urlToImage,
        publishedAt = value.publishedAt?.toDateFormatted(),
        description = value.description,
    )
}