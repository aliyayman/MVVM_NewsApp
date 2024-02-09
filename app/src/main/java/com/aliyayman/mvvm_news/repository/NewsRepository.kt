package com.aliyayman.mvvm_news.repository

import com.aliyayman.mvvm_news.api.RetrofitInstance
import com.aliyayman.mvvm_news.db.ArticleDatabase

class NewsRepository (
    val db : ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String,pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery: String,pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)
}