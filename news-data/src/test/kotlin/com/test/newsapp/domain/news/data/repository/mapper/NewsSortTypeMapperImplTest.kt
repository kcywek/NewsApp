package com.test.newsapp.domain.news.data.repository.mapper

import com.test.newsapp.data.network.rest.model.request.NewsSortTypeRequest
import com.test.newsapp.data.repository.mapper.news.NewsSortTypeMapperImpl
import com.test.newsapp.domain.common.model.NewsSortTypeEntity
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class NewsSortTypeMapperImplTest {

    private val mapper = NewsSortTypeMapperImpl()

    @Test
    fun `maps RELEVANCY type`() {
        Assertions.assertEquals(
            mapper.from(NewsSortTypeEntity.RELEVANCY),
            NewsSortTypeRequest.RELEVANCY
        )
    }

    @Test
    fun `maps POPULARITY type`() {
        Assertions.assertEquals(
            mapper.from(NewsSortTypeEntity.POPULARITY),
            NewsSortTypeRequest.POPULARITY
        )
    }

    @Test
    fun `maps PUBLISHED_AT type`() {
        Assertions.assertEquals(
            mapper.from(NewsSortTypeEntity.PUBLISHED_AT),
            NewsSortTypeRequest.PUBLISHED_AT
        )
    }
}