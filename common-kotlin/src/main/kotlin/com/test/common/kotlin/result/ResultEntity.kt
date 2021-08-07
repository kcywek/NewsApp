@file:Suppress("UNCHECKED_CAST", "RedundantVisibilityModifier", "MemberVisibilityCanBePrivate", "unused", "TooGenericExceptionCaught")

package com.test.common.kotlin.result

import com.test.common.kotlin.result.ResultEntity.Companion.failure
import com.test.common.kotlin.result.ResultEntity.Companion.nothing
import com.test.common.kotlin.result.ResultEntity.Companion.success

/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

typealias ResultEntityNothing = ResultEntity<NoResult>

object NoResult

/**
 * Scrapped from {@link kotlin.Result } which is inline class and don't work with mockito
 * TODO update it each kotlin version, that's from 1.4.30
 */
public class ResultEntity<out T> @PublishedApi internal constructor(
    @PublishedApi
    internal val value: Any?
) {
    // discovery

    /**
     * Returns `true` if this instance represents a successful outcome.
     * In this case [isFailure] returns `false`.
     */
    public val isSuccess: Boolean get() = value !is Failure

    /**
     * Returns `true` if this instance represents a failed outcome.
     * In this case [isSuccess] returns `false`.
     */
    public val isFailure: Boolean get() = value is Failure

    // value & exception retrieval

    /**
     * Returns the encapsulated value if this instance represents [success][ResultEntity.isSuccess] or `null`
     * if it is [failure][ResultEntity.isFailure].
     *
     * This function is a shorthand for `getOrElse { null }` (see [getOrElse]) or
     * `fold(onSuccess = { it }, onFailure = { null })` (see [fold]).
     */

    public fun getOrNull(): T? =
        when {
            isFailure -> null
            else -> value as T
        }

    /**
     * Returns the encapsulated [Throwable] exception if this instance represents [failure][isFailure] or `null`
     * if it is [success][isSuccess].
     *
     * This function is a shorthand for `fold(onSuccess = { null }, onFailure = { it })` (see [fold]).
     */
    public fun exceptionOrNull(): Throwable? =
        when (value) {
            is Failure -> value.exception
            else -> null
        }

    /**
     * Returns a string `Success(v)` if this instance represents [success][ResultEntity.isSuccess]
     * where `v` is a string representation of the value or a string `Failure(x)` if
     * it is [failure][isFailure] where `x` is a string representation of the exception.
     */
    public override fun toString(): String =
        when (value) {
            is Failure -> value.toString() // "Failure($exception)"
            else -> "Success($value)"
        }

    // companion with constructors

    /**
     * Companion object for [ResultEntity] class that contains its constructor functions
     * [success] and [failure].
     */
    public companion object {
        /**
         * Returns an instance that encapsulates the given [value] as successful value.
         */
        @Suppress("INAPPLICABLE_JVM_NAME")

        @JvmName("success")
        public fun <T> success(value: T): ResultEntity<T> =
            ResultEntity(value)

        /**
         * Returns an instance that encapsulates the given [Throwable] [exception] as failure.
         */
        @Suppress("INAPPLICABLE_JVM_NAME")

        @JvmName("failure")
        public fun <T> failure(exception: Throwable): ResultEntity<T> =
            ResultEntity(createFailure(exception))

        @JvmName("nothing")
        public fun nothing(): ResultEntityNothing =
            success(NoResult)
    }

    internal class Failure(
        @JvmField
        val exception: Throwable
    ) {
        override fun equals(other: Any?): Boolean = other is Failure && exception == other.exception
        override fun hashCode(): Int = exception.hashCode()
        override fun toString(): String = "Failure($exception)"
    }

    /**
     * Returns the encapsulated value if this instance represents [success][ResultEntity.isSuccess] or throws the encapsulated [Throwable] exception
     * if it is [failure][ResultEntity.isFailure].
     *
     * This function is a shorthand for `getOrElse { throw it }` (see [getOrElse]).
     */
    public fun getOrThrow(): T {
        throwOnFailure()
        return value as T
    }

    /**
     * Returns the encapsulated value if this instance represents [success][ResultEntity.isSuccess] or the
     * result of [onFailure] function for the encapsulated [Throwable] exception if it is [failure][ResultEntity.isFailure].
     *
     * Note, that this function rethrows any [Throwable] exception thrown by [onFailure] function.
     *
     * This function is a shorthand for `fold(onSuccess = { it }, onFailure = onFailure)` (see [fold]).
     */
    public inline fun <R, T : R> ResultEntity<T>.getOrElse(onFailure: (exception: Throwable) -> R): R {
        return when (val exception = exceptionOrNull()) {
            null -> value as T
            else -> onFailure(exception)
        }
    }

    /**
     * Returns the encapsulated value if this instance represents [success][ResultEntity.isSuccess] or the
     * [defaultValue] if it is [failure][ResultEntity.isFailure].
     *
     * This function is a shorthand for `getOrElse { defaultValue }` (see [getOrElse]).
     */
    public fun <R, T : R> ResultEntity<T>.getOrDefault(defaultValue: R): R {
        if (isFailure) return defaultValue
        return value as T
    }

    /**
     * Returns the result of [onSuccess] for the encapsulated value if this instance represents [success][ResultEntity.isSuccess]
     * or the result of [onFailure] function for the encapsulated [Throwable] exception if it is [failure][ResultEntity.isFailure].
     *
     * Note, that this function rethrows any [Throwable] exception thrown by [onSuccess] or by [onFailure] function.
     */
    public inline fun <R> fold(
        onSuccess: (value: T) -> R,
        onFailure: (exception: Throwable) -> R
    ): R {
        return when (val exception = exceptionOrNull()) {
            null -> onSuccess(value as T)
            else -> onFailure(exception)
        }
    }

// transformation

    /**
     * Returns the encapsulated result of the given [transform] function applied to the encapsulated value
     * if this instance represents [success][ResultEntity.isSuccess] or the
     * original encapsulated [Throwable] exception if it is [failure][ResultEntity.isFailure].
     *
     * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
     * See [mapCatching] for an alternative that encapsulates exceptions.
     */
    public inline fun <R> map(transform: (value: T) -> R): ResultEntity<R> {
        return when {
            isSuccess -> success(transform(value as T).checkResult())
            else -> ResultEntity(value)
        }
    }

    fun <T> T.checkResult() =
        if (this is ResultEntity<*>) {
            throw IllegalStateException("use `then` or ResultEntityNothing")
        } else {
            this
        }

    /**
     * Returns the encapsulated result of the given [transform] function applied to the encapsulated value
     * if this instance represents [success][ResultEntity.isSuccess] or the
     * original encapsulated [Throwable] exception if it is [failure][ResultEntity.isFailure].
     *
     * This function catches any [Throwable] exception thrown by [transform] function and encapsulates it as a failure.
     * See [map] for an alternative that rethrows exceptions from `transform` function.
     */
    public inline fun <R> mapCatching(transform: (value: T) -> R): ResultEntity<R> {
        return when {
            isSuccess -> runCatching { transform(value as T) }
            else -> ResultEntity(value)
        }
    }

    public inline fun <R> then(transform: (value: T) -> ResultEntity<R>): ResultEntity<R> {
        return when {
            isSuccess -> transform(value as T)
            else -> ResultEntity(value)
        }
    }

    public inline fun <R, X> recoverFrom(classType: Class<X>, transform: () -> ResultEntity<R>): ResultEntity<R> {
        val exception = exceptionOrNull()
        return when {
            exception == null -> ResultEntity(value)
            classType.isInstance(exception) -> transform()
            else -> ResultEntity(exception)
        }
    }

    /**
     * Returns the encapsulated result of the given [transform] function applied to the encapsulated [Throwable] exception
     * if this instance represents [failure][ResultEntity.isFailure] or the
     * original encapsulated value if it is [success][ResultEntity.isSuccess].
     *
     * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
     * See [recoverCatching] for an alternative that encapsulates exceptions.
     */
    public inline fun <R, T : R> ResultEntity<T>.recover(transform: (exception: Throwable) -> R): ResultEntity<R> {
        return when (val exception = exceptionOrNull()) {
            null -> this
            else -> success(transform(exception))
        }
    }

    /**
     * Returns the encapsulated result of the given [transform] function applied to the encapsulated [Throwable] exception
     * if this instance represents [failure][ResultEntity.isFailure] or the
     * original encapsulated value if it is [success][ResultEntity.isSuccess].
     *
     * This function catches any [Throwable] exception thrown by [transform] function and encapsulates it as a failure.
     * See [recover] for an alternative that rethrows exceptions.
     */
    public inline fun <R, T : R> ResultEntity<T>.recoverCatching(transform: (exception: Throwable) -> R): ResultEntity<R> {
        return when (val exception = exceptionOrNull()) {
            null -> this
            else -> runCatching { transform(exception) }
        }
    }

// "peek" onto value/exception and pipe

    /**
     * Performs the given [action] on the encapsulated [Throwable] exception if this instance represents [failure][ResultEntity.isFailure].
     * Returns the original `Result` unchanged.
     */
    public inline fun onFailure(action: (exception: Throwable) -> Unit): ResultEntity<T> {
        exceptionOrNull()?.let { action(it) }
        return this
    }

    /**
     * Performs the given [action] on the encapsulated value if this instance represents [success][ResultEntity.isSuccess].
     * Returns the original `Result` unchanged.
     */
    public inline fun onSuccess(action: (value: T) -> Unit): ResultEntity<T> {
        if (isSuccess) action(value as T)
        return this
    }
}

/**
 * Creates an instance of internal marker [ResultEntity.Failure] class to
 * make sure that this class is not exposed in ABI.
 */
@PublishedApi
internal fun createFailure(exception: Throwable): Any =
    ResultEntity.Failure(exception)

/**
 * Throws exception if the result is failure. This internal function minimizes
 * inlined bytecode for [getOrThrow] and makes sure that in the future we can
 * add some exception-augmenting logic here (if needed).
 */
@PublishedApi
internal fun ResultEntity<*>.throwOnFailure() {
    if (value is ResultEntity.Failure) throw value.exception
}

/**
 * Calls the specified function [block] and returns its encapsulated result if invocation was successful,
 * catching any [Throwable] exception that was thrown from the [block] function execution and encapsulating it as a failure.
 */
public inline fun <R> runCatching(block: () -> R): ResultEntity<R> {
    return try {
        ResultEntity.success(block())
    } catch (e: Throwable) {
        ResultEntity.failure(e)
    }
}

/**
 * Calls the specified function [block] with `this` value as its receiver and returns its encapsulated result if invocation was successful,
 * catching any [Throwable] exception that was thrown from the [block] function execution and encapsulating it as a failure.
 */
public inline fun <T, R> T.runCatching(block: T.() -> R): ResultEntity<R> {
    return try {
        ResultEntity.success(block())
    } catch (e: Throwable) {
        ResultEntity.failure(e)
    }
}

public inline fun <A, B, R> combine(
    resultA: ResultEntity<A>,
    resultB: ResultEntity<B>,
    transform: (A, B) -> ResultEntity<R>,
): ResultEntity<R> {
    if (resultA.isFailure) return ResultEntity(resultA.value)
    if (resultB.isFailure) return ResultEntity(resultB.value)
    return transform(resultA.value as A, resultB.value as B)
}

public fun <A, B> combine(
    resultA: ResultEntity<A>,
    resultB: ResultEntity<B>,
): ResultEntity<Pair<A, B>> =
    combine(resultA, resultB) { a, b ->
        success(a to b)
    }

public inline fun <A, B, C, R> combine(
    resultA: ResultEntity<A>,
    resultB: ResultEntity<B>,
    resultC: ResultEntity<C>,
    transform: (A, B, C) -> ResultEntity<R>,
): ResultEntity<R> {
    if (resultA.isFailure) return ResultEntity(resultA.value)
    if (resultB.isFailure) return ResultEntity(resultB.value)
    if (resultC.isFailure) return ResultEntity(resultC.value)
    return transform(resultA.value as A, resultB.value as B, resultC.value as C)
}

public fun <A, B, C> combine(
    resultA: ResultEntity<A>,
    resultB: ResultEntity<B>,
    resultC: ResultEntity<C>,
): ResultEntity<Triple<A, B, C>> =
    combine(resultA, resultB, resultC) { a, b, c ->
        success(Triple(a, b, c))
    }

public inline fun <A, B, C, D, R> combine(
    resultA: ResultEntity<A>,
    resultB: ResultEntity<B>,
    resultC: ResultEntity<C>,
    resultD: ResultEntity<D>,
    transform: (A, B, C, D) -> ResultEntity<R>,
): ResultEntity<R> {
    if (resultA.isFailure) return ResultEntity(resultA.value)
    if (resultB.isFailure) return ResultEntity(resultB.value)
    if (resultC.isFailure) return ResultEntity(resultC.value)
    if (resultD.isFailure) return ResultEntity(resultD.value)
    return transform(resultA.value as A, resultB.value as B, resultC.value as C, resultD.value as D)
}

public inline fun <A, B, C, D, E, F, R> combine(
    resultA: ResultEntity<A>,
    resultB: ResultEntity<B>,
    resultC: ResultEntity<C>,
    resultD: ResultEntity<D>,
    resultE: ResultEntity<E>,
    resultF: ResultEntity<F>,
    transform: (A, B, C, D, E, F) -> ResultEntity<R>,
): ResultEntity<R> {
    if (resultA.isFailure) return ResultEntity(resultA.value)
    if (resultB.isFailure) return ResultEntity(resultB.value)
    if (resultC.isFailure) return ResultEntity(resultC.value)
    if (resultD.isFailure) return ResultEntity(resultD.value)
    if (resultE.isFailure) return ResultEntity(resultE.value)
    if (resultF.isFailure) return ResultEntity(resultF.value)
    return transform(
        resultA.value as A, resultB.value as B, resultC.value as C,
        resultD.value as D, resultE.value as E, resultF.value as F,
    )
}

public inline fun <A, B, C, D, E, F, G, H, R> combine(
    resultA: ResultEntity<A>,
    resultB: ResultEntity<B>,
    resultC: ResultEntity<C>,
    resultD: ResultEntity<D>,
    resultE: ResultEntity<E>,
    resultF: ResultEntity<F>,
    resultG: ResultEntity<G>,
    resultH: ResultEntity<H>,
    transform: (A, B, C, D, E, F, G, H) -> ResultEntity<R>,
): ResultEntity<R> {
    if (resultA.isFailure) return ResultEntity(resultA.value)
    if (resultB.isFailure) return ResultEntity(resultB.value)
    if (resultC.isFailure) return ResultEntity(resultC.value)
    if (resultD.isFailure) return ResultEntity(resultD.value)
    if (resultE.isFailure) return ResultEntity(resultE.value)
    if (resultF.isFailure) return ResultEntity(resultF.value)
    if (resultG.isFailure) return ResultEntity(resultG.value)
    if (resultH.isFailure) return ResultEntity(resultH.value)
    return transform(
        resultA.value as A, resultB.value as B, resultC.value as C, resultD.value as D,
        resultE.value as E, resultF.value as F, resultG.value as G, resultH.value as H
    )
}

public fun <T> List<ResultEntity<T>>.combine(): ResultEntity<List<T>> {
    iterator().forEach {
        if (it.isFailure) return ResultEntity(it.value)
    }
    return ResultEntity.success(map { it.getOrThrow() })
}

public inline fun <A, B> ResultEntity<A>.combineWith(
    combiner: (A) -> ResultEntity<B>
): ResultEntity<Pair<A, B>> =
    then { firstValue ->
        combiner(firstValue)
            .map { secondValue -> firstValue to secondValue }
    }

public fun validate(vararg results: ResultEntity<Any>): ResultEntityNothing {
    results.forEach {
        if (it.isFailure) return ResultEntity(it.value)
    }
    return nothing()
}

public fun validate(results: List<ResultEntity<Any>>): ResultEntityNothing {
    results.forEach {
        if (it.isFailure) return ResultEntity(it.value)
    }
    return nothing()
}

fun <T> ResultEntity<T?>.requireNotNull(throwableFactory: () -> Throwable): ResultEntity<T> =
    if (value == null) failure(throwableFactory())
    else ResultEntity(value)

fun <T> T?.requireNotNull(throwableFactory: () -> Throwable): ResultEntity<T> =
    let(::success).requireNotNull { throwableFactory() }

fun <T> ResultEntity<T?>.requireNotNull(message: String): ResultEntity<T> =
    requireNotNull { IllegalStateException(message) }

fun <T> T?.requireNotNull(message: String): ResultEntity<T> =
    let(::success).requireNotNull { IllegalStateException(message) }
