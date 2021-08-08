package com.test.newsapp.data.network.rest.model

internal data class ArticleResponse (
    val title: String,
    val author: String?,
    val description: String,
    val url: String,
    val content: String,
)
