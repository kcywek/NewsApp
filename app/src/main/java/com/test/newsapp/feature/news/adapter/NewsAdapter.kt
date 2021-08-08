package com.test.newsapp.feature.news.adapter

import com.test.newsapp.feature.news.model.NewsItem

internal interface NewsAdapter {

    fun submitList(list: List<NewsItem>?)
}
