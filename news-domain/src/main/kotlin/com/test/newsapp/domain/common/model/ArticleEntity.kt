package com.test.newsapp.domain.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleEntity(
    val title: String,
    val author: String?,
    val description: String,
    val url: String,
    val urlToImage: String,
    val content: String,
) : Parcelable