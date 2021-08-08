package com.test.newsapp.feature.news.router

import androidx.navigation.NavController
import com.test.newsapp.domain.common.model.ArticleEntity
import com.test.newsapp.feature.news.ui.NewsFragmentDirections

internal class NewsRouterImpl(
    private val navController: NavController
) : NewsRouter {

    override fun navigateToNewsDetails(news: ArticleEntity) {
        navController.navigate(NewsFragmentDirections.navigateToNewsDetails(news))
    }

}
