package com.test.newsapp.feature.news.mapper

import com.test.newsapp.feature.news.fakeArticlesList
import com.test.newsapp.feature.news.fakeNewsItems
import com.test.newsapp.feature.news.model.NewsState
import com.test.newsapp.feature.news.model.NewsViewState
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class NewsStateMapperTest {

    private val mapper = NewsStateMapper()

    @Test
    fun `when no article then maps to news view state with no results visible`() {
        val state = NewsState(
            items = listOf(),
        )
        val expectedState = NewsViewState(
            items = listOf(),
            isResultsVisible = false,
            isNoResultsVisible = true,
        )

        Assertions.assertEquals(expectedState, mapper.from(state))
    }

    @Test
    fun `when articles are present then maps to news view state with results visible`() {
        val state = NewsState(
            items = fakeArticlesList,
        )
        val expectedState = NewsViewState(
            items = fakeNewsItems,
            isResultsVisible = true,
            isNoResultsVisible = false,
        )

        Assertions.assertEquals(expectedState, mapper.from(state))
    }
}