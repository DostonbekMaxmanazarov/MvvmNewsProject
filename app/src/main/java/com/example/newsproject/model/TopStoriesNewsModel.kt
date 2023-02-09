package com.example.newsproject.model

data class TopStoriesNewsModel(
    val topNews: List<TopStoriesNewsItemModel>
) : BaseNewsModel()
