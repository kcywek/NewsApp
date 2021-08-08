package com.test.newsapp.data.network.rest.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.common.BuildConfig
import com.test.newsapp.data.network.rest.interceptor.AuthenticationInterceptor
import com.test.newsapp.data.network.rest.service.PosRestService
import com.test.newsapp.data.network.rest.util.ApiKeyProvider
import com.test.newsapp.data.network.rest.util.NewsApiKeyProvider
import com.test.newsapp.data.network.rest.util.NewsRestUrlProvider
import com.test.newsapp.data.network.rest.util.UrlProvider
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

val networkModule = module {

    single<Moshi>(named(NEWS_MOSHI)) {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single(named(NEWS_OKHTTP)) {
        OkHttpClient.Builder()
            .cache(
                Cache(
                    directory = File(get<Context>().cacheDir, "news-http-cache"),
                    maxSize = CACHE_SIZE
                )
            )
            .writeTimeout(WRITE_TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SEC, TimeUnit.SECONDS)
            .addInterceptor(
                AuthenticationInterceptor(
                    apiKeyProvider = get(),
                )
            )
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }
            .build()
    }

    factory<PosRestService> {
        Retrofit.Builder()
            .client(get(named(NEWS_OKHTTP)))
            .baseUrl(get<UrlProvider>().baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(get(named(NEWS_MOSHI))))
            .build()
            .create(PosRestService::class.java)
    }

    factory<ApiKeyProvider> {
        NewsApiKeyProvider()
    }

    factory<UrlProvider> {
        NewsRestUrlProvider()
    }
}

const val NEWS_MOSHI = "NEWS_MOSHI"
const val NEWS_OKHTTP = "NEWS_OKHTTP"

private const val WRITE_TIMEOUT_SEC = 120L
private const val READ_TIMEOUT_SEC = 120L
private const val CACHE_SIZE = 10 * 1024 * 1024L
