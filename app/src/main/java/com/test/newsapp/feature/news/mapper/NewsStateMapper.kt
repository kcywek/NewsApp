package com.test.newsapp.feature.news.mapper

import com.test.newsapp.feature.news.model.NewsState
import com.test.newsapp.feature.news.model.NewsViewState

internal class NewsStateMapper {

    fun from(state: NewsState): NewsViewState {

        return NewsViewState(
            state.text
        )
    }
}
