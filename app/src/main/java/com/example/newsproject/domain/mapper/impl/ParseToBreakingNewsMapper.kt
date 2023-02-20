package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.local.entity.CategoryNewsEntity
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.CategoryNewsItemModel

class ParseToBreakingNewsMapper : ISingleMapper<CategoryNewsEntity, CategoryNewsItemModel> {
    override fun invoke(value: CategoryNewsEntity): CategoryNewsItemModel = CategoryNewsItemModel(
        name = value.name.toString(),
        title = value.title,
        imageUrl = value.imageUrl,
        publishedAt = value.publishedAt,
        content = value.description,
    )
}