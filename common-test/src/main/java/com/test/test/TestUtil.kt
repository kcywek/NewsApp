package com.test.test

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.coroutines.coroutineContext
import kotlin.time.Duration

fun <T> CoroutineScope.assertFlowEquals(flow: Flow<T>, vararg expected: T) {
    val collected = mutableListOf<T>()
    launch(start = CoroutineStart.UNDISPATCHED, context = Dispatchers.Unconfined) {
        flow.collectIndexed { index, item ->
            collected.add(item)
            if (index == expected.size - 1) cancel()
        }
    }
    assertEquals(expected.asList(), collected)
}

suspend fun <T> assertFlowEqualsWithTimeout(flow: Flow<T>, vararg expected: T) {
    Dispatchers.setMain(coroutineContext[CoroutineDispatcher.Key] as TestCoroutineDispatcher)

    val collected = mutableListOf<T>()
    try {
        withTimeout(Duration.milliseconds(1000)) {
            flow.collect { item ->
                collected.add(item)
            }
            assertEquals(expected.asList(), collected)
        }
    } catch (ignored: TimeoutCancellationException) {
        assertEquals(expected.asList(), collected)
    }
}

suspend fun <T> Flow<T>.isEmpty(): Boolean =
    try {
        withTimeout(Duration.milliseconds(10)) {
            first()
            false
        }
    } catch (e: TimeoutCancellationException) {
        true
    }

inline fun <reified T : Throwable> assertThrows(noinline executable: suspend () -> Unit) {
    Assertions.assertThrows(T::class.java) {
        runBlocking { executable() }
    }
    Unit
}
