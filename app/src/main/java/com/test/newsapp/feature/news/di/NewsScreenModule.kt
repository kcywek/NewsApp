package com.test.newsapp.feature.news.di

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import com.test.newsapp.feature.news.mapper.NewsStateMapper
import com.test.newsapp.feature.news.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsScreenModule = module {

    factory {
        NewsStateMapper()
    }

    //TODO: move to more general module
    factory<InputMethodManager> { checkNotNull(get<Context>().getSystemService()) }

    viewModel {
        NewsViewModel(
            newsUseCase = get(),
            mapper = get(),
        )
    }
}
