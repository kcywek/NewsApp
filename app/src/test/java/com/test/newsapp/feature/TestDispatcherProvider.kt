package com.test.newsapp.feature

import com.test.common.util.dispacher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

object TestDispatcherProvider : DispatcherProvider {

    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    override val default: CoroutineDispatcher = testDispatcher
    override val io: CoroutineDispatcher = testDispatcher
    override val main: CoroutineDispatcher = testDispatcher
    override val unconfined: CoroutineDispatcher = testDispatcher

    fun advanceTimeBy(delayTimeMillis: Long) {
        testDispatcher.advanceTimeBy(delayTimeMillis)
    }

    fun advanceUntilIdle() {
        testDispatcher.advanceUntilIdle()
    }
}
