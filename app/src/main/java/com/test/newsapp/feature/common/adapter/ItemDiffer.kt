package com.test.newsapp.feature.common.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class ItemDiffer<T : Any>(private val getIdentifier: (T) -> Any) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        getIdentifier(oldItem) == getIdentifier(newItem)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem
}
