package com.test.common.util.delegates

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

private class LifecycleAwareLazy<out T>(
    private val lifecycle: Lifecycle,
    private val initializer: () -> T
) : Lazy<T>, LifecycleEventObserver {

    private var _value: Any? = UninitializedValue

    override val value: T
        get() {
            if (_value === UninitializedValue) {
                _value = initializer.invoke()
            }
            @Suppress("UNCHECKED_CAST")
            return _value as T
        }

    init {
        lifecycle.addObserver(this)
    }

    override fun isInitialized(): Boolean =
        _value !== UninitializedValue

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_STOP) {
            _value = UninitializedValue
        }
    }

    private fun finalize() {
        lifecycle.removeObserver(this)
    }

    private object UninitializedValue
}

fun <T> Fragment.lazyViewLifecycle(initializer: () -> T): Lazy<T> =
    LifecycleAwareLazy(lifecycle, initializer)

fun <T> AppCompatActivity.lazyViewLifecycle(initializer: () -> T): Lazy<T> =
    LifecycleAwareLazy(lifecycle, initializer)
