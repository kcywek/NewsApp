package com.test.newsapp.feature.news.router

import com.test.newsapp.domain.common.model.ArticleEntity

internal interface NewsRouter {

    fun navigateToNewsDetails(news: ArticleEntity)
}
