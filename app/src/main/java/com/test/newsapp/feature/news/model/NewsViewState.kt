package com.test.newsapp.feature.news.model

internal data class NewsViewState(
    val items: List<NewsItem>,
    val isResultsVisible: Boolean,
    val isNoResultsVisible: Boolean,
)
