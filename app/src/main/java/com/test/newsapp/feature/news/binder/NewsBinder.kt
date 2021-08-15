package com.test.newsapp.feature.news.binder

import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.test.common.util.binding.Binder
import com.test.newsapp.feature.common.extensions.onAnimationEnd
import com.test.newsapp.feature.news.adapter.NewsAdapter
import com.test.newsapp.feature.news.model.NewsViewState

internal class NewsBinder(
    private val adapter: NewsAdapter,
    private val viewPager: ViewPager2,
    private val noResults: TextView,
) : Binder<NewsViewState> {

    override fun bind(viewState: NewsViewState) = with(viewState) {
        adapter.submitList(items)

        viewPager.isVisible = isResultsVisible
        if(isResultsVisible) {
            val fadeIn = AlphaAnimation(0f, 1f)
            fadeIn.apply {
                interpolator = AccelerateInterpolator()
                startOffset = ANIMATION_OFFSET
                duration = ANIMATION_DURATION
                onAnimationEnd {
                    val halfOfPages = Integer.MAX_VALUE / 2
                    viewPager.setCurrentItem(halfOfPages - halfOfPages % items.size, false)
                }
                viewPager.startAnimation(this)
            }
        }
        noResults.isVisible = isNoResultsVisible
    }

    companion object {
        const val ANIMATION_DURATION = 500L
        const val ANIMATION_OFFSET = 500L
    }
}
