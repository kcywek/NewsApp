package com.test.newsapp.data.repository.mapper.news

import com.test.newsapp.data.network.rest.model.NewsResponse
import com.test.newsapp.domain.common.model.NewsEntity

internal class NewsMapperImpl : NewsMapper {

    override fun from(newsResponse: NewsResponse): NewsEntity =
        NewsEntity(
            status = newsResponse.status,
        )
}
