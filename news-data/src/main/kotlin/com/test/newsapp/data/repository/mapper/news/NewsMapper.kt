package com.test.newsapp.data.repository.mapper.news

import com.test.newsapp.data.network.rest.model.NewsResponse
import com.test.newsapp.domain.common.model.NewsEntity

internal interface NewsMapper {

    fun from(newsResponse: NewsResponse): NewsEntity
}
