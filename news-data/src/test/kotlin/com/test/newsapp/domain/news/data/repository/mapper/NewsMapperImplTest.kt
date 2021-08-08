package com.test.newsapp.domain.news.data.repository.mapper

import com.test.newsapp.data.repository.mapper.news.NewsMapperImpl
import com.text.newsapp.fakeArticleList
import com.text.newsapp.fakeNewsResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class NewsMapperImplTest {

    private val mapper = NewsMapperImpl()

    @Test
    fun `maps news response to articles list`() {
        Assertions.assertEquals(
            mapper.from(fakeNewsResponse),
            fakeArticleList
        )
    }
}