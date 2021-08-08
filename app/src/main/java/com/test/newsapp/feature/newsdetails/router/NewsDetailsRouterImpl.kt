package com.test.newsapp.feature.newsdetails.router

import android.content.Context
import android.content.Intent
import android.net.Uri

internal class NewsDetailsRouterImpl(
    private val context: Context,
) : NewsDetailsRouter {

    override fun showUrl(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }

}
