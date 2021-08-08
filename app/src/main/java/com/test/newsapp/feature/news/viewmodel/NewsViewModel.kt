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
import com.test.newsapp.feature.news.model.NewsItem
import com.test.newsapp.feature.news.model.NewsState
import com.test.newsapp.feature.news.model.NewsViewState
import com.test.newsapp.feature.news.router.NewsRouter
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

internal class NewsViewModel(
    private val newsUseCase: FetchNewsUseCase,
    mapper: NewsStateMapper,
) : ViewModel() {

    private val state = NullSafeMutableLiveData(NewsState())
    private val currentState: NewsState get() = checkNotNull(state.value)
    val viewState: LiveData<NewsViewState> = state.mapInBackground(mapper::from)

    private val _effect: Channel<NewsEffect> = Channel()
    val effect: Flow<NewsEffect> = _effect.receiveAsFlow()

    fun onSearchTriggered(query: String) {
        state.reduce {
            copy(
                query = query,
            )
        }
        updateState()
    }

    fun onSortTypeTriggered(sortBy: NewsSortTypeEntity) {
        state.reduce {
            copy(
                sortBy = sortBy,
            )
        }
        updateState()
    }

    fun onFromChanged(from: ZonedDateTime) {
        state.reduce {
            copy(
                from = from,
            )
        }
        updateState()
    }

    private fun updateState() {
        if (currentState.query.length >= MINIMUM_QUERY_LENGTH) {
            viewModelScope.launch {
                _effect.send(NewsEffect.ShowProgressDialog)
                newsUseCase.run(
                    query = currentState.query,
                    sortBy = currentState.sortBy,
                    from = currentState.from,
                ).onSuccess {
                    _effect.send(NewsEffect.HideProgressDialog)
                    state.reduce {
                        copy(items = it)
                    }
                }.onFailure {
                    _effect.send(NewsEffect.HideProgressDialog)
                    throw it
                }
            }
        } else {
            viewModelScope.launch(dispatcherProvider.main) {
                _effect.send(NewsEffect.ShowError(R.string.news_list_minimum_query_length_error))
            }
        }
    }

    fun onNewsClicked(router: NewsRouter, newsItem: NewsItem) {
        router.navigateToNewsDetails(
            currentState.items.first { it.title == newsItem.title }
        )
    }

    companion object {
        const val MINIMUM_QUERY_LENGTH = 2
    }
}
