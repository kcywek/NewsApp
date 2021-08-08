package com.test.newsapp.feature.newsdetails.viewmodel

import com.test.newsapp.feature.InstantTaskExecutorExtension
import com.test.newsapp.feature.TestDispatcherProvider
import com.test.newsapp.feature.news.fakeArticle
import com.test.newsapp.feature.newsdetails.mapper.NewsDetailsStateMapper
import com.test.newsapp.feature.newsdetails.router.NewsDetailsRouter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExtendWith(InstantTaskExecutorExtension::class)
internal class NewsDetailsViewModelTest {

    private val mockNewsDetailsStateMapper = mock<NewsDetailsStateMapper>()
    private val mockRouter = mock<NewsDetailsRouter>()

    private val viewModel = NewsDetailsViewModel(
        articleEntity = fakeArticle,
        mapper = mockNewsDetailsStateMapper,
    )

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(TestDispatcherProvider.default)
    }

    @Test
    fun `when onShowFullNews then shows url`(): Unit = runBlocking {
        viewModel.onShowFullNews(mockRouter)

        verify(mockRouter).showUrl(any())
    }
}