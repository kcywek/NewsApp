package com.test.newsapp.feature.news.model

import com.test.newsapp.domain.common.model.ArticleEntity

internal data class NewsState(
    val items: List<ArticleEntity> = listOf(),
)
