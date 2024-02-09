package com.aliyayman.mvvm_news.ui.activty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

import com.aliyayman.mvvm_news.R
import com.aliyayman.mvvm_news.databinding.ActivityNewsBinding
import com.aliyayman.mvvm_news.db.ArticleDatabase
import com.aliyayman.mvvm_news.repository.NewsRepository
import com.aliyayman.mvvm_news.viewmodels.NewsViewModel
import com.aliyayman.mvvm_news.viewmodels.NewsViewModelProviderFactory

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
     lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createBottomNavigation()
    }

    private fun createBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navHostFragment.navController
        )

    }


    // d413ff4c60294cd5b64374a0153c0d2a

}
