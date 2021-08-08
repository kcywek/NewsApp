package com.test.newsapp.domain.news.data.repository

import com.test.newsapp.data.network.rest.model.request.NewsSortTypeRequest
import com.test.newsapp.data.network.rest.service.PosRestService
import com.test.newsapp.data.repository.NewsRepositoryImpl
import com.test.newsapp.data.repository.mapper.news.NewsMapper
import com.test.newsapp.data.repository.mapper.news.NewsSortTypeMapper
import com.test.newsapp.domain.common.model.NewsSortTypeEntity
import com.text.newsapp.fakeArticleList
import com.text.newsapp.fakeNewsResponse
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.time.ZoneId
import java.time.ZonedDateTime

internal class NewsRepositoryImplTest {

    private val mockPosRestService = mock<PosRestService>()
    private val mockNewsMapper = mock<NewsMapper> {
        onBlocking { from(any()) } doReturn fakeArticleList
    }
    private val mockNewsSortTypeMapper = mock<NewsSortTypeMapper> {
        on { from(any()) } doReturn NewsSortTypeRequest.PUBLISHED_AT
    }

    private val repository = NewsRepositoryImpl(
        posRestService = mockPosRestService,
        newsMapper = mockNewsMapper,
        newsSortTypeMapper = mockNewsSortTypeMapper,
    )

    @Test
    fun `when fetching news then return article list`() = runBlocking {
        val query = "Apple"
        val sortBy = NewsSortTypeEntity.PUBLISHED_AT
        val from = ZonedDateTime.of(2021, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault())

        whenever(mockPosRestService.getNews(any(), any(), any())).thenReturn(fakeNewsResponse)

        val result = repository.fetchNews(query, sortBy, from).getOrThrow()

        //TODO: skip testing mapping from value - will be switched to ZoneDateTime with moshi adapter
        verify(mockPosRestService).getNews(query, NewsSortTypeRequest.PUBLISHED_AT, "2021-01-01")
        verify(mockNewsSortTypeMapper).from(sortBy)
        verify(mockNewsMapper).from(fakeNewsResponse)
        Assertions.assertEquals(fakeArticleList, result)
    }

}