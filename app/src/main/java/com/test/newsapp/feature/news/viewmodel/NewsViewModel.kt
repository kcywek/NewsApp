package com.test.newsapp.feature.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.common.util.extensions.NullSafeMutableLiveData
import com.test.common.util.extensions.mapInBackground
import com.test.common.util.extensions.reduce
import com.test.newsapp.domain.common.model.NewsSortTypeEntity
import com.test.newsapp.domain.news.FetchNewsUseCase
import com.test.newsapp.feature.news.mapper.NewsStateMapper
import com.test.newsapp.feature.news.model.NewsState
import com.test.newsapp.feature.news.model.NewsViewState
import kotlinx.coroutines.launch

internal class NewsViewModel(
    newsUseCase: FetchNewsUseCase,
    mapper: NewsStateMapper,
) : ViewModel() {

    private val state = NullSafeMutableLiveData(NewsState(""))
    val viewState: LiveData<NewsViewState> = state.mapInBackground(mapper::from)

    init {
        viewModelScope.launch {
            newsUseCase.run(
                query = "Apple",
                sortBy = NewsSortTypeEntity.POPULARITY
            )
                .onSuccess {
                    state.reduce {
                        copy(text = it.status)
                    }
                }
                .onFailure {
                    throw it
                }
        }
    }
}
