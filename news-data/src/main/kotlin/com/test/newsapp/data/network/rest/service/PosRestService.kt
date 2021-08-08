package com.test.newsapp.data.network.rest.service

import com.test.newsapp.data.network.rest.model.NewsResponse
import com.test.newsapp.data.network.rest.model.request.NewsSortTypeRequest
import retrofit2.http.GET
import retrofit2.http.Query

internal interface PosRestService {

    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("sortBy") sortBy: NewsSortTypeRequest,
        @Query("from") from: String, //TODO: replace with ZoneDateTime & create json adapter
    ): NewsResponse

}
