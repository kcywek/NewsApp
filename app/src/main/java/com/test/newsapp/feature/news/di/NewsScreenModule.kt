package com.test.newsapp.feature.news.di

import com.test.newsapp.feature.news.mapper.NewsStateMapper
import com.test.newsapp.feature.news.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsScreenModule = module {

    factory {
        NewsStateMapper()
    }

    viewModel {
        NewsViewModel(
            newsUseCase = get(),
            mapper = get(),
        )
    }
}
