package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.CategoryNewsModel

class ParseToCategoryNewsMapper : ISingleMapper<CategoryNewsEntity, CategoryNewsModel> {
    override fun invoke(value: CategoryNewsEntity): CategoryNewsModel = CategoryNewsModel(
        name = value.name.toString(),
        title = value.title,
        imageUrl = value.imageUrl,
        publishedAt = value.publishedAt,
        description = value.description,
    )
}