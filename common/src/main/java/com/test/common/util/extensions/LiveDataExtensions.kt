package com.test.common.util.extensions

import androidx.annotation.MainThread
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.test.common.util.dispacher.DispatcherProviderWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

inline fun <T> NullSafeMutableLiveData<T>.reduce(block: T.() -> T) {
    value = block(value)
}

class NullSafeMutableLiveData<T>(value: T) : MutableLiveData<T>(value) {

    override fun getValue(): T = checkNotNull(super.getValue())
}

@PublishedApi
internal class ObserverImpl<T>(
    lifecycleOwner: LifecycleOwner,
    private val flow: Flow<T>,
    private val collector: suspend (T) -> Unit
) : DefaultLifecycleObserver {

    private var job: Job? = null

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onStart(owner: LifecycleOwner) {
        job = owner.lifecycleScope.launch {
            flow.collect {
                collector(it)
            }
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        job?.cancel()
        job = null
    }
}

// todo chek if that works better https://medium.com/androiddevelopers/a-safer-way-to-collect-flows-from-android-uis-23080b1f8bda
fun <T> Flow<T>.observe(
    lifecycleOwner: LifecycleOwner,
    collector: suspend (T) -> Unit
) {
    ObserverImpl(lifecycleOwner, this, collector)
}

@MainThread
fun <X, Y> LiveData<X>.mapInBackground(mapFunction: (X) -> Y): LiveData<Y> {
    val result = MediatorLiveData<Y>()

    result.addSource(this, Observer<X> { x ->
        if (x == null) return@Observer
        CoroutineScope(DispatcherProviderWrapper.provider.default).launch {
            result.postValue(mapFunction(x))
        }
    })

    return result
}
