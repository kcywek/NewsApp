package com.test.newsapp.feature.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<in T>(
    itemView: View
) : RecyclerView.ViewHolder(itemView), LayoutContainer {

    abstract fun bind(item: T)
}

abstract class BindingViewHolder<in T, V : ViewBinding>(
    block: (inflater: LayoutInflater, container: ViewGroup, attach: Boolean) -> V,
    parent: ViewGroup,
    @StyleRes theme: Int = 0,
    protected val viewBinding: V = block(LayoutInflater.from(parent.context.wrapInTheme(theme)), parent, false)
) : BaseViewHolder<T>(viewBinding.root), LayoutContainer {

    override val containerView: View
        get() = viewBinding.root

    final override fun bind(item: T) {
        viewBinding.bind(item)
    }

    abstract fun V.bind(item: T)
}

private fun Context.wrapInTheme(@StyleRes theme: Int = 0): Context =
    if (theme != 0) {
        ContextThemeWrapper(this, theme)
    } else {
        this
    }
