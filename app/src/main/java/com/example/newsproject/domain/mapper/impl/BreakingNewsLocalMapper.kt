package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.local.entity.BreakingNewsEntity
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.BreakingNewsItemModel

class BreakingNewsLocalMapper : ISingleMapper<ArticleItemResponse, BreakingNewsEntity> {
    override fun invoke(value: ArticleItemResponse): BreakingNewsEntity = BreakingNewsEntity(
        name = value.source?.name.toString(),
        title = value.title,
        imageUrl = value.urlToImage,
        publishedAt = value.publishedAt,
        description = value.content,
    )
}