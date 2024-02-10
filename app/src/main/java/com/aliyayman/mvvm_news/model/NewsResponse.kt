package com.aliyayman.mvvm_news.model




data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)