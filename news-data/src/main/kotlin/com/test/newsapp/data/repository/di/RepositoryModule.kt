package com.test.newsapp.data.repository.di

import com.test.newsapp.data.repository.NewsRepositoryImpl
import com.test.newsapp.data.repository.mapper.news.NewsMapper
import com.test.newsapp.data.repository.mapper.news.NewsMapperImpl
import com.test.newsapp.data.repository.mapper.news.NewsSortTypeMapper
import com.test.newsapp.data.repository.mapper.news.NewsSortTypeMapperImpl
import com.test.newsapp.domain.news.NewsRepository
import org.koin.dsl.module

internal val repositoryModule = module {

    factory<NewsRepository> {
        NewsRepositoryImpl(
            posRestService = get(),
            newsMapper = get(),
            newsSortTypeMapper = get(),
        )
    }

    factory<NewsMapper> {
        NewsMapperImpl()
    }

    factory<NewsSortTypeMapper> {
        NewsSortTypeMapperImpl()
    }
}
