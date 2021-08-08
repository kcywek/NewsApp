package com.test.newsapp.di

import com.test.newsapp.feature.news.di.newsScreenModule
import com.test.newsapp.feature.newsdetails.di.newsDetailsScreenModule

val presentationModules = listOf(
    newsScreenModule,
    newsDetailsScreenModule,
)
