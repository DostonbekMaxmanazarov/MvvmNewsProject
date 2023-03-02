package com.example.newsproject.domain.mapper.impl

import com.example.newsproject.datasource.remote.response.ArticleDataResponse
import com.example.newsproject.domain.mapper.ISingleMapper
import com.example.newsproject.model.SearchNewsModel
import com.example.newsproject.util.extension.toDateFormatted

class SearchNewsMapper : ISingleMapper<ArticleDataResponse, SearchNewsModel> {
    override fun invoke(value: ArticleDataResponse): SearchNewsModel = SearchNewsModel(
        name = value.source?.name,
        title = value.title,
        imageUrl = value.urlToImage,
        publishedAt = value.publishedAt?.toDateFormatted(),
        description = value.description
    )
}