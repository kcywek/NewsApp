package com.test.newsapp.domain.news

import com.test.common.kotlin.result.ResultEntity
import com.test.newsapp.domain.common.model.ArticleEntity
import com.test.newsapp.domain.common.model.NewsSortTypeEntity
import java.time.ZonedDateTime

class FetchNewsUseCase(
    private val newsRepository: NewsRepository
) {

    suspend fun run(
        query: String,
        sortBy: NewsSortTypeEntity,
        from: ZonedDateTime,
    ): ResultEntity<List<ArticleEntity>> = newsRepository.fetchNews(query, sortBy, from)

}
