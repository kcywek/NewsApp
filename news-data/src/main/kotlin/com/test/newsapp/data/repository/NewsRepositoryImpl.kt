package com.test.newsapp.data.repository

import com.test.common.kotlin.result.ResultEntity
import com.test.newsapp.data.network.rest.service.PosRestService
import com.test.newsapp.data.repository.mapper.news.NewsMapper
import com.test.newsapp.data.repository.mapper.news.NewsSortTypeMapper
import com.test.newsapp.domain.common.model.ArticleEntity
import com.test.newsapp.domain.common.model.NewsSortTypeEntity
import com.test.newsapp.domain.news.NewsRepository
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

internal class NewsRepositoryImpl(
    private val posRestService: PosRestService,
    private val newsMapper: NewsMapper,
    private val newsSortTypeMapper: NewsSortTypeMapper,
) : NewsRepository {

    override suspend fun fetchNews(
        query: String,
        sortBy: NewsSortTypeEntity,
        from: ZonedDateTime,
    ): ResultEntity<List<ArticleEntity>> =
        ResultEntity.success(
            newsMapper.from(
                posRestService.getNews(
                    query = query,
                    sortBy = newsSortTypeMapper.from(sortBy),
                    from = simpleDateFormatter.format(from)
                )
            )
        )

    private companion object {
        val simpleDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }
}
