package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.BookmarkNewsModel

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