package com.test.newsapp.feature.news

import com.test.newsapp.domain.common.model.ArticleEntity
import com.test.newsapp.feature.news.model.NewsItem

val fakeArticlesList = listOf(
    ArticleEntity(
        title = "",
        author = "",
        description = "",
        url = "",
        urlToImage = "",
        content = "",
    )
)

val fakeNewsItems = listOf(
    NewsItem(
        title = "",
        description = "",
    )
)

val fakeArticle = ArticleEntity(
    title = "title",
    author = "",
    description = "",
    url = "",
    urlToImage = "imageUrl",
    content = "content",
)