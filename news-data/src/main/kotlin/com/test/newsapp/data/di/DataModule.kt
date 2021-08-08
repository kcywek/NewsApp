package com.test.newsapp.data.di

import com.test.newsapp.data.network.rest.di.networkModule
import com.test.newsapp.data.repository.di.repositoryModule

val dataModule = listOf(
    networkModule,
    repositoryModule,
)
