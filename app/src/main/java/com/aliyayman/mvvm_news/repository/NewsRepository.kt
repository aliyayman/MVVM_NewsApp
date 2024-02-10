package com.aliyayman.mvvm_news.repository

import com.aliyayman.mvvm_news.api.RetrofitInstance
import com.aliyayman.mvvm_news.db.ArticleDatabase
import com.aliyayman.mvvm_news.model.Article

class NewsRepository (
    val db : ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String,pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery: String,pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)


    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}