package com.test.newsapp.feature.news.router

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.test.newsapp.domain.common.model.ArticleEntity
import com.test.newsapp.feature.InstantTaskExecutorExtension
import com.test.newsapp.feature.news.ui.NewsFragmentDirections
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExtendWith(InstantTaskExecutorExtension::class)
internal class NewsRouterImplTest {

    private val mockGraph = mock<NavGraph>()
    private val navController = mock<NavController> {
        on { graph } doReturn mockGraph
    }

    private val router = NewsRouterImpl(
        navController = navController,
    )

    @Test
    fun `navigates to news details screen`() {
        val articleEntity = ArticleEntity(
            title = "title",
            author = "",
            description = "",
            url = "",
            urlToImage = "imageUrl",
            content = "content",
        )
        router.navigateToNewsDetails(articleEntity)

        verify(navController).navigate(NewsFragmentDirections.navigateToNewsDetails(articleEntity))
    }
}