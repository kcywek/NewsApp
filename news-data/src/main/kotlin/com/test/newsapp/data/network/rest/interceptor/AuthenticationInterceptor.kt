package com.test.newsapp.data.network.rest.interceptor

import com.test.newsapp.data.network.rest.util.ApiKeyProvider
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

internal class AuthenticationInterceptor(
    private val apiKeyProvider: ApiKeyProvider,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val url = original.url.newBuilder()
            .addQueryParameter("apiKey", apiKeyProvider.apiKey)
            .build()

        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}
