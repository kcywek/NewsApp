package com.test.newsapp.feature.news.model

import androidx.annotation.StringRes

internal sealed class NewsEffect {

    data class ShowError(@StringRes val text: Int) : NewsEffect()

    object ShowProgressDialog : NewsEffect()

    object HideProgressDialog : NewsEffect()
}
