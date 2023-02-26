package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.datasource.remote.response.ArticleDataResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.BookmarkNewsModel
import com.example.newsproject.model.SearchNewsModel
import com.example.newsproject.util.toDateFormatted

class BookmarkNewsModelMapper : ISingleMapper<BookmarkNewsEntity, BookmarkNewsModel> {
    override fun invoke(value: BookmarkNewsEntity): BookmarkNewsModel = BookmarkNewsModel(
        id = value.id,
        name = value.name,
        title = value.title,
        imageUrl = value.imageUrl,
        publishedAt = value.publishedAt,
        description = value.description
    )
}