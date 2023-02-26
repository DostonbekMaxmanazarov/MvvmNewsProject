package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.local.entity.BookmarkNewsEntity
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.CategoryNewsModel

class CategoryBookmarkNewsLocalMapper : ISingleMapper<CategoryNewsModel, BookmarkNewsEntity> {
    override fun invoke(value: CategoryNewsModel): BookmarkNewsEntity = BookmarkNewsEntity(
        name = value.name,
        title = value.title,
        imageUrl = value.imageUrl,
        publishedAt = value.publishedAt,
        description = value.description,
    )
}