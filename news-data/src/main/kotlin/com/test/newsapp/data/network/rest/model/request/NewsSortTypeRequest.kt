package com.test.newsapp.data.network.rest.model.request

import com.google.gson.annotations.SerializedName

enum class NewsSortTypeRequest {
    @SerializedName("relevancy")
    RELEVANCY,
    @SerializedName("popularity")
    POPULARITY,
    @SerializedName("publishedAt")
    PUBLISHED_AT,
}
