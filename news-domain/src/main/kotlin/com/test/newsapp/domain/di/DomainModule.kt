package com.test.newsapp.domain.di

import com.test.newsapp.domain.news.FetchNewsUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        FetchNewsUseCase(
            newsRepository = get()
        )
    }
}

val domainModule = listOf(
    useCaseModule
)
