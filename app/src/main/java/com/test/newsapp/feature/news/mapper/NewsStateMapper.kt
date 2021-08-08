package com.test.newsapp.feature.news.mapper

import com.test.newsapp.feature.news.model.NewsItem
import com.test.newsapp.feature.news.model.NewsState
import com.test.newsapp.feature.news.model.NewsViewState

internal class NewsStateMapper {

    fun from(state: NewsState): NewsViewState =
        NewsViewState(
            isNoResultsVisible = state.items.isEmpty(),
            isResultsVisible = state.items.isNotEmpty(),
            items = state.items
                .map {
                    NewsItem(
                        title = it.title,
                        description = it.description
                    )
                }
        )
}
