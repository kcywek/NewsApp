package com.test.newsapp.feature.newsdetails.mapper

import com.test.newsapp.feature.news.fakeArticle
import com.test.newsapp.feature.newsdetails.model.NewsDetailsState
import com.test.newsapp.feature.newsdetails.model.NewsDetailsViewState
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class NewsDetailsStateMapperTest {

    private val mapper = NewsDetailsStateMapper()

    @Test
    fun `maps to news details view state`() {
        val state = NewsDetailsState(
            news = fakeArticle,
        )
        val expectedState = NewsDetailsViewState(
            title = "title",
            content = "content",
            imageUrl = "imageUrl",
        )

        Assertions.assertEquals(expectedState, mapper.from(state))
    }
}