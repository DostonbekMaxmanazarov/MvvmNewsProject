package com.example.newsproject.model

data class CategoryNewsModel(
    val breakingNews: List<CategoryNewsItemModel>
) : BaseNewsModel()
