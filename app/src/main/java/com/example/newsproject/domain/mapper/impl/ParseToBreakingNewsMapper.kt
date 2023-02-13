package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.local.entity.BreakingNewsEntity
import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.BreakingNewsItemModel
import com.example.newsproject.model.TopStoriesNewsItemModel

class ParseToBreakingNewsMapper : ISingleMapper<BreakingNewsEntity, BreakingNewsItemModel> {
    override fun invoke(value: BreakingNewsEntity): BreakingNewsItemModel = BreakingNewsItemModel(
        name = value.name.toString(),
        title = value.title,
        imageUrl = value.imageUrl,
        publishedAt = value.publishedAt,
        content = value.description,
    )
}