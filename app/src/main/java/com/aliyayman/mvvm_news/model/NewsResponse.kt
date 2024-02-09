package com.aliyayman.mvvm_news.model

import androidx.room.Entity

@Entity
data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)