package com.test.newsapp.data.repository.mapper.news

import com.test.newsapp.data.network.rest.model.NewsResponse
import com.test.newsapp.domain.common.model.ArticleEntity

internal class NewsMapperImpl : NewsMapper {

    override fun from(newsResponse: NewsResponse): List<ArticleEntity> =
        newsResponse.articles
            .map {
                with(it) {
                    ArticleEntity(
                        title = title,
                        author = author,
                        description = description,
                        urlToImage = urlToImage,
                        url = url,
                        content = content,
                    )
                }
            }
}
