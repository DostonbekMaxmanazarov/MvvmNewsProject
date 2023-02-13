package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.remote.response.ArticleItemResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.TopStoriesNewsItemModel
import com.example.newsproject.util.toDateFormatted

class TopNewsMapper : ISingleMapper<ArticleItemResponse, TopStoriesNewsItemModel> {
    override fun invoke(value: ArticleItemResponse): TopStoriesNewsItemModel =
        TopStoriesNewsItemModel(
            name = value.source?.name,
            title = value.title,
            imageUrl = value.urlToImage,
            publishedAt = value.publishedAt?.toDateFormatted(),
            content = value.description,
        )
}