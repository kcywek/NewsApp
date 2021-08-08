package com.test.newsapp.feature.news.binder

import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.test.common.util.binding.Binder
import com.test.newsapp.feature.news.adapter.NewsAdapter
import com.test.newsapp.feature.news.model.NewsViewState

internal class NewsBinder(
    private val adapter: NewsAdapter,
    private val recyclerView: RecyclerView,
    private val noResults: TextView,
) : Binder<NewsViewState> {

    override fun bind(viewState: NewsViewState) = with(viewState) {
        adapter.submitList(items)
        recyclerView.isVisible = isResultsVisible
        noResults.isVisible = isNoResultsVisible
    }
}
