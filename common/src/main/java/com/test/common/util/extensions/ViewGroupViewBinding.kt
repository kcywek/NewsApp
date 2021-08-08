package com.test.common.util.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewGroupViewBinding<T : ViewBinding>(
    viewGroup: ViewGroup,
    viewBindingFactory: (LayoutInflater, ViewGroup, Boolean) -> T
) : ReadOnlyProperty<ViewGroup, T> {

    private val binding: T = viewBindingFactory(
        LayoutInflater.from(viewGroup.context),
        viewGroup,
        true
    )

    override fun getValue(thisRef: ViewGroup, property: KProperty<*>): T =
        binding
}

fun <T : ViewBinding> ViewGroup.viewBinding(viewBindingFactory: (LayoutInflater, ViewGroup) -> T) =
    ViewGroupViewBinding(this) { inflater, viewGroup, _ ->
        viewBindingFactory(inflater, viewGroup)
    }

fun <T : ViewBinding> ViewGroup.viewBinding(viewBindingFactory: (LayoutInflater, ViewGroup, Boolean) -> T) =
    ViewGroupViewBinding(this, viewBindingFactory)
