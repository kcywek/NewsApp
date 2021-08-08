package com.test.newsapp.domain.news

import com.test.newsapp.domain.common.model.ArticleEntity

internal val fakeArticleList = listOf(
    ArticleEntity(
        title = "title",
        author = "author",
        description = "lorem ipsum",
        url = "https://example.com/",
        content = "lorem ipsum dolor sit amet",
    )
)