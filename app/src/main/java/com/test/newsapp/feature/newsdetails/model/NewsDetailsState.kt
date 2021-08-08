package com.test.newsapp.feature.newsdetails.model

import com.test.newsapp.domain.common.model.ArticleEntity

internal data class NewsDetailsState(
    val news: ArticleEntity,
)