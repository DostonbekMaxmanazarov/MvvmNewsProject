package com.example.newsproject.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsproject.util.Constants.BREAKING_NEWS_TABLE

@Entity(BREAKING_NEWS_TABLE)
data class BreakingNewsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val imageUrl: String,
    val name: String,
    val title: String,
    val description: String,
    val publishedAt: String
)