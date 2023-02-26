package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.remote.response.ArticleDataResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.CategoryNewsModel
import com.example.newsproject.util.toDateFormatted

class CategoryNewsMapper : ISingleMapper<ArticleDataResponse, CategoryNewsModel> {
    override fun invoke(value: ArticleDataResponse): CategoryNewsModel = CategoryNewsModel(
        name = value.source?.name,
        title = value.title,
        imageUrl = value.urlToImage,
        publishedAt = value.publishedAt?.toDateFormatted(),
        description = value.description,
    )
}