package com.test.newsapp.feature.news.model

import com.test.newsapp.domain.common.model.ArticleEntity
import com.test.newsapp.domain.common.model.NewsSortTypeEntity
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

internal data class NewsState(
    val items: List<ArticleEntity> = listOf(),
    val query: String = "",
    val sortBy: NewsSortTypeEntity = NewsSortTypeEntity.PUBLISHED_AT,
    val from: ZonedDateTime = DEFAULT_FROM_DATE
)

val DEFAULT_FROM_DATE: ZonedDateTime = ZonedDateTime.of(
    Calendar.getInstance().get(Calendar.YEAR),
    Calendar.getInstance().get(Calendar.MONTH) + 1,
    1, 0, 0, 0, 0, ZoneId.systemDefault())
