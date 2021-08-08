package com.test.newsapp.domain.news

import com.test.common.kotlin.result.ResultEntity
import com.test.newsapp.domain.common.model.ArticleEntity
import com.test.newsapp.domain.common.model.NewsSortTypeEntity
import java.time.ZonedDateTime

interface NewsRepository {

    suspend fun fetchNews(
        query: String,
        sortBy: NewsSortTypeEntity,
        from: ZonedDateTime,
    ): ResultEntity<List<ArticleEntity>>

}
