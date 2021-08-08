package com.test.newsapp.domain.common.model

data class ArticleEntity(
    val title: String,
    val author: String?,
    val description: String,
    val url: String,
    val urlToImage: String,
    val content: String,
)