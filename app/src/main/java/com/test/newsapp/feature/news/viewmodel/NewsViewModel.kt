package com.test.newsapp.feature.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.common.util.dispacher.dispatcherProvider
import com.test.common.util.extensions.NullSafeMutableLiveData
import com.test.common.util.extensions.mapInBackground
import com.test.common.util.extensions.reduce
import com.test.newsapp.R
import com.test.newsapp.domain.common.model.NewsSortTypeEntity
import com.test.newsapp.domain.news.FetchNewsUseCase
import com.test.newsapp.feature.news.mapper.NewsStateMapper
import com.test.newsapp.feature.news.model.NewsEffect
import com.test.newsapp.feature.news.model.NewsState
import com.test.newsapp.feature.news.model.NewsViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

internal class NewsViewModel(
    private val newsUseCase: FetchNewsUseCase,
    mapper: NewsStateMapper,
) : ViewModel() {

    private val state = NullSafeMutableLiveData(NewsState())
    val viewState: LiveData<NewsViewState> = state.mapInBackground(mapper::from)

    private val _effect: Channel<NewsEffect> = Channel()
    val effect: Flow<NewsEffect> = _effect.receiveAsFlow()

    private suspend fun getNews(query: String) {
        newsUseCase.run(
            query = query,
            sortBy = NewsSortTypeEntity.POPULARITY
        )
            .onSuccess {
                state.reduce {
                    copy(items = it)
                }
            }
            .onFailure {
                throw it
            }
    }

    fun onSearchTriggered(query: String) {
        if (query.length >= MINIMUM_QUERY_LENGTH) {
            viewModelScope.launch {
                getNews(query)
            }
        } else {
            viewModelScope.launch(dispatcherProvider.main) {
                _effect.send(NewsEffect.ShowError(R.string.news_list_minimum_query_length_error))
            }
        }
    }

    companion object {
        const val MINIMUM_QUERY_LENGTH = 2
    }
}
