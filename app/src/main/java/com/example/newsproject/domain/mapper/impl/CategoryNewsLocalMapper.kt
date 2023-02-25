package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.datasource.remote.response.ArticleDataResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.util.toDateFormatted

class CategoryNewsLocalMapper : ISingleMapper<ArticleDataResponse, CategoryNewsEntity> {
    override fun invoke(value: ArticleDataResponse): CategoryNewsEntity = CategoryNewsEntity(
        name = value.source?.name.toString(),
        title = value.title,
        imageUrl = value.urlToImage,
        publishedAt = value.publishedAt?.toDateFormatted(),
        description = value.description,
    )
}