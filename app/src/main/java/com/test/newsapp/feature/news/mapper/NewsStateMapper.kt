package com.test.newsapp.feature.news.mapper

import com.test.newsapp.feature.news.model.NewsItem
import com.test.newsapp.feature.news.model.NewsState
import com.test.newsapp.feature.news.model.NewsViewState

internal class NewsStateMapper {

    fun from(state: NewsState): NewsViewState = with(state) {
        NewsViewState(
            isNoResultsVisible = items.isEmpty(),
            isResultsVisible = items.isNotEmpty(),
            items = items
                .map {
                    NewsItem(
                        title = it.title,
                        description = it.description
                    )
                },
        )
    }
}
