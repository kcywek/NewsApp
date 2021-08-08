package com.test.newsapp.feature.news.mapper.viewmodel

import com.test.newsapp.R
import com.test.newsapp.domain.common.model.NewsSortTypeEntity
import com.test.newsapp.domain.news.FetchNewsUseCase
import com.test.newsapp.feature.InstantTaskExecutorExtension
import com.test.newsapp.feature.TestDispatcherProvider
import com.test.newsapp.feature.news.mapper.NewsStateMapper
import com.test.newsapp.feature.news.model.DEFAULT_FROM_DATE
import com.test.newsapp.feature.news.model.NewsEffect
import com.test.newsapp.feature.news.viewmodel.NewsViewModel
import com.test.test.assertFlowEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.time.ZonedDateTime

@ExtendWith(InstantTaskExecutorExtension::class)
internal class NewsViewModelTest {

    private val mockFetchNewsUseCase = mock<FetchNewsUseCase>()
    private val mockNewsStateMapper = mock<NewsStateMapper>()

    private val viewModel = NewsViewModel(
        newsUseCase = mockFetchNewsUseCase,
        mapper = mockNewsStateMapper,
    )

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(TestDispatcherProvider.default)
    }

    @Test
    fun `when onSearchTriggered with proper query then fetches news`(): Unit = runBlocking {
        val query = "test"

        viewModel.onSearchTriggered(query)

        verify(mockFetchNewsUseCase).run(
            query = query,
            sortBy = NewsSortTypeEntity.PUBLISHED_AT,
            from = DEFAULT_FROM_DATE,
        )
    }

    @Test
    fun `when onSearchTriggered with empty query emits ShowError effect`(): Unit = runBlocking {
        val query = ""

        viewModel.onSearchTriggered(query)

        assertFlowEquals(
            viewModel.effect,
            NewsEffect.ShowError(R.string.news_list_minimum_query_length_error),
        )
    }

    @Test
    fun `when onSortTypeTriggered with proper query then fetches news`(): Unit = runBlocking {
        val query = "test"
        val sortBy = NewsSortTypeEntity.POPULARITY

        viewModel.onSortTypeTriggered(sortBy)
        viewModel.onSearchTriggered(query)

        verify(mockFetchNewsUseCase).run(
            query = query,
            sortBy = sortBy,
            from = DEFAULT_FROM_DATE,
        )
    }

    @Test
    fun `when onFromChanged triggered and proper query set then fetches news from this time`(): Unit =
        runBlocking {
            val query = "test"
            val from = ZonedDateTime.now()

            viewModel.onSearchTriggered(query)
            viewModel.onFromChanged(from)

            verify(mockFetchNewsUseCase).run(
                query = query,
                sortBy = NewsSortTypeEntity.PUBLISHED_AT,
                from = from,
            )
        }
}