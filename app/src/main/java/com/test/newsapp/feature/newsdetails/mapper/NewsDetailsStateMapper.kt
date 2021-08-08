package com.test.newsapp.feature.newsdetails.mapper

import com.test.newsapp.feature.newsdetails.model.NewsDetailsState
import com.test.newsapp.feature.newsdetails.model.NewsDetailsViewState

internal class NewsDetailsStateMapper {

    fun from(state: NewsDetailsState): NewsDetailsViewState = with(state) {
        NewsDetailsViewState(
            title = news.title,
            description = news.content,
            imageUrl = news.urlToImage,
        )
    }
}
