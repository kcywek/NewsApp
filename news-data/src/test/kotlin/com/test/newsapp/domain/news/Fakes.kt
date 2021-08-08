package com.text.newsapp

import com.test.newsapp.data.network.rest.model.ArticleResponse
import com.test.newsapp.data.network.rest.model.NewsResponse
import com.test.newsapp.domain.common.model.ArticleEntity

internal val fakeNewsResponse = NewsResponse(
    listOf(
        ArticleResponse(
            title = "title",
            author = "author",
            description = "lorem ipsum",
            url = "https://example.com/",
            urlToImage = "https://example-img.com/",
            content = "lorem ipsum dolor sit amet",
        )
    )
)

internal val fakeArticleList = listOf(
    ArticleEntity(
        title = "title",
        author = "author",
        description = "lorem ipsum",
        url = "https://example.com/",
        urlToImage = "https://example-img.com/",
        content = "lorem ipsum dolor sit amet",
    )
)