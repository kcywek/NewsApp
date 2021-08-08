package com.test.newsapp.feature.news.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.test.newsapp.databinding.ItemNewsBinding
import com.test.newsapp.feature.common.adapter.BaseViewHolder
import com.test.newsapp.feature.common.adapter.BindingViewHolder
import com.test.newsapp.feature.common.adapter.ItemDiffer
import com.test.newsapp.feature.news.model.NewsItem

internal class NewsAdapterImpl : ListAdapter<NewsItem, BaseViewHolder<NewsItem>>(
    ItemDiffer(NewsItem::hashCode)
), NewsAdapter {

    var onNewsClickListener: ((NewsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<NewsItem> =
        ItemValueViewHolder(parent)

    override fun onBindViewHolder(holder: BaseViewHolder<NewsItem>, position: Int) =
        holder.bind(getItem(position))

    inner class ItemValueViewHolder(parent: ViewGroup) :
        BindingViewHolder<NewsItem, ItemNewsBinding>(
            ItemNewsBinding::inflate,
            parent
        ) {

        override fun ItemNewsBinding.bind(item: NewsItem) {
            itemView.setOnClickListener { onNewsClickListener?.invoke(item) }
            title.text = item.title
            description.text = item.description
        }
    }
}
