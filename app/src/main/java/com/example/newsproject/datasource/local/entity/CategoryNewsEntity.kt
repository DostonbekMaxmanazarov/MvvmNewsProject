package com.example.newsproject.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsproject.util.Constants

@Entity(Constants.CATEGORY_NEWS_TABLE)
data class CategoryNewsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageUrl: String?,
    val name: String?,
    val title: String?,
    val description: String?,
    val publishedAt: String?
)