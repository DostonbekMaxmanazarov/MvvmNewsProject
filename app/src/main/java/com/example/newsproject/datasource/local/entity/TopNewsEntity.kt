package com.example.newsproject.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsproject.util.Constants.TOP_NEWS_TABLE

@Entity(TOP_NEWS_TABLE)
data class TopNewsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val imageUrl: String,
    val name: String,
    val title: String,
    val description: String,
    val publishedAt: String
)