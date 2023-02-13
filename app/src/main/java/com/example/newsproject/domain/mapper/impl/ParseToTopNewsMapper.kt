package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.local.entity.TopNewsEntity
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.BreakingNewsItemModel
import com.example.newsproject.model.TopStoriesNewsItemModel

class ParseToTopNewsMapper : ISingleMapper<TopNewsEntity, TopStoriesNewsItemModel> {
    override fun invoke(value: TopNewsEntity): TopStoriesNewsItemModel = TopStoriesNewsItemModel(
        name = value.name.toString(),
        title = value.title,
        imageUrl = value.imageUrl,
        publishedAt = value.publishedAt,
        content = value.description,
    )
}