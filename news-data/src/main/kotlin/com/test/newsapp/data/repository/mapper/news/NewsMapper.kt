package com.test.newsapp.data.repository.mapper.news

import com.test.newsapp.data.network.rest.model.NewsResponse
import com.test.newsapp.domain.common.model.ArticleEntity

internal interface NewsMapper {

    fun from(newsResponse: NewsResponse): List<ArticleEntity>
}
