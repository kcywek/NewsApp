package com.test.newsapp.domain.news

import com.test.common.kotlin.result.ResultEntity.Companion.success
import com.test.newsapp.domain.common.model.NewsSortTypeEntity
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.time.ZonedDateTime

internal class FetchNewsUseCaseTest {

    private val mockNewsRepository = mock<NewsRepository>() {
        onBlocking { fetchNews(any(), any(), any()) } doReturn success(fakeArticleList)
    }

    private val useCase = FetchNewsUseCase(
        newsRepository = mockNewsRepository,
    )

    @Test
    fun `gets articles list`() = runBlocking {
        val query = "test"
        val sortType = NewsSortTypeEntity.RELEVANCY
        val from = ZonedDateTime.now()

        val result = useCase.run(query,sortType, from).getOrNull()

        verify(mockNewsRepository).fetchNews(query, sortType,from)
        assertEquals(fakeArticleList, result)
    }
}