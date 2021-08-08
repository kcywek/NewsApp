package com.test.newsapp.feature.newsdetails.binder

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.test.common.util.binding.Binder
import com.test.newsapp.feature.newsdetails.model.NewsDetailsViewState

internal class NewsDetailsBinder(
    private val titleView: TextView,
    private val descriptionView: TextView,
    private val imageView: ImageView,
) : Binder<NewsDetailsViewState> {

    override fun bind(viewState: NewsDetailsViewState) = with(viewState) {
        Glide.with(imageView)
            .load(imageUrl)
            .into(imageView)
        titleView.text = title
        descriptionView.text = content
    }
}
