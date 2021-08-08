package com.test.newsapp.feature.newsdetails.di

import com.test.newsapp.feature.newsdetails.mapper.NewsDetailsStateMapper
import com.test.newsapp.feature.newsdetails.viewmodel.NewsDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsDetailsScreenModule = module {

    factory {
        NewsDetailsStateMapper()
    }

    viewModel { params ->
        NewsDetailsViewModel(
            articleEntity = params.get(),
            mapper = get(),
        )
    }
}
