package com.test.newsapp.feature.newsdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.test.common.util.extensions.NullSafeMutableLiveData
import com.test.common.util.extensions.mapInBackground
import com.test.newsapp.domain.common.model.ArticleEntity
import com.test.newsapp.feature.news.model.NewsState
import com.test.newsapp.feature.newsdetails.mapper.NewsDetailsStateMapper
import com.test.newsapp.feature.newsdetails.model.NewsDetailsState
import com.test.newsapp.feature.newsdetails.model.NewsDetailsViewState
import com.test.newsapp.feature.newsdetails.router.NewsDetailsRouter

internal class NewsDetailsViewModel(
    private val mapper: NewsDetailsStateMapper,
    articleEntity: ArticleEntity,
): ViewModel() {
    private val state = NullSafeMutableLiveData(NewsDetailsState(articleEntity))
    private val currentState: NewsDetailsState get() = checkNotNull(state.value)
    val viewState: LiveData<NewsDetailsViewState> = state.mapInBackground(mapper::from)

    fun onShowFullNews(router: NewsDetailsRouter) {
        router.showUrl(currentState.news.url)
    }
}
