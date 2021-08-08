package com.test.newsapp.domain.news

import com.test.common.kotlin.result.ResultEntity
import com.test.newsapp.domain.common.model.NewsEntity
import com.test.newsapp.domain.common.model.NewsSortTypeEntity
import java.time.ZonedDateTime

class FetchNewsUseCase(
    private val newsRepository: NewsRepository
) {

    suspend fun run(
        query: String,
        sortBy: NewsSortTypeEntity,
    ): ResultEntity<NewsEntity> = newsRepository.fetchNews(query, sortBy, ZonedDateTime.now().minusDays(7))

}