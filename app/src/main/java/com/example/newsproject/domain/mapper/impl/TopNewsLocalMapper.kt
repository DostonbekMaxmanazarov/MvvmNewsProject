package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.local.entity.TopNewsEntity
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.util.toDateFormatted

class TopNewsLocalMapper : ISingleMapper<ArticleItemResponse, TopNewsEntity> {
    override fun invoke(value: ArticleItemResponse): TopNewsEntity = TopNewsEntity(
        name = value.source?.name.toString(),
        title = value.title,
        imageUrl = value.urlToImage,
        publishedAt = value.publishedAt?.toDateFormatted(),
        description = value.description,
    )
}