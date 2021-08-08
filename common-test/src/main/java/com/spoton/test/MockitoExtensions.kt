@file:Suppress("TestFunctionName", "unused", "ObjectPropertyName")

package com.test.test

import org.mockito.kotlin.KArgumentCaptor
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.whenever

/**
 * Documentation https://spotonteam.atlassian.net/wiki/spaces/OMNICHANNE/pages/983465987/invokeWheneverCapture
 */

private typealias CaptorFnc <T> = KArgumentCaptor<T>.(KArgumentCaptor<T>) -> Any

//region Zero Arguments
fun invokeLastWheneverCapture(
    function: CaptorFnc<() -> Unit>
) = invokeLastWheneverCaptureWithResult(Unit, function)

fun invokeLastWheneverCaptureWithResult(
    result: Any,
    function: CaptorFnc<() -> Unit>,
) = _wheneverCapture(function, result, { lastValue() })

fun invokeWheneverCapture(
    function: CaptorFnc<() -> Unit>
) = invokeWheneverCaptureWithResult(Unit, function)

fun invokeWheneverCaptureWithResult(
    result: Any,
    function: CaptorFnc<() -> Unit>,
) = _wheneverCapture(function, result, { lastValue() }, _throwIfCapturedTwice)
//endregion

//region One Argument
fun <A> invokeLastWheneverCapture(
    argA: A,
    function: CaptorFnc<(A) -> Unit>
) = invokeLastWheneverCaptureWithResult(argA, Unit, function)

fun <A> invokeLastWheneverCaptureWithResult(
    argA: A,
    result: Any,
    function: CaptorFnc<(A) -> Unit>
) = _wheneverCapture(function, result, { lastValue(argA) })

fun <A> invokeWheneverCapture(
    argA: A,
    function: CaptorFnc<(A) -> Unit>
) = invokeWheneverCaptureWithResult(argA, Unit, function)

fun <A> invokeWheneverCaptureWithResult(
    argA: A,
    result: Any,
    function: CaptorFnc<(A) -> Unit>
) = _wheneverCapture(function, result, { lastValue(argA) }, _throwIfCapturedTwice)
//endregion

//region Two Arguments
fun <A, B> invokeLastWheneverCapture(
    argA: A,
    argB: B,
    function: CaptorFnc<(A, B) -> Unit>
) = invokeLastWheneverCaptureWithResult(argA, argB, Unit, function)

fun <A, B> invokeLastWheneverCaptureWithResult(
    argA: A,
    argB: B,
    result: Any,
    function: CaptorFnc<(A, B) -> Unit>
) = _wheneverCapture(function, result, { lastValue(argA, argB) })

fun <A, B> invokeWheneverCapture(
    argA: A,
    argB: B,
    function: CaptorFnc<(A, B) -> Unit>
) = invokeWheneverCaptureWithResult(argA, argB, Unit, function)

fun <A, B> invokeWheneverCaptureWithResult(
    argA: A,
    argB: B,
    result: Any,
    function: CaptorFnc<(A, B) -> Unit>
) = _wheneverCapture(function, result, { lastValue(argA, argB) }, _throwIfCapturedTwice)
//endregion

//region Three Arguments
fun <A, B, C> invokeLastWheneverCapture(
    argA: A,
    argB: B,
    argC: C,
    function: CaptorFnc<(A, B, C) -> Unit>
) = invokeLastWheneverCaptureWithResult(argA, argB, argC, Unit, function)

fun <A, B, C> invokeLastWheneverCaptureWithResult(
    argA: A,
    argB: B,
    argC: C,
    result: Any,
    function: CaptorFnc<(A, B, C) -> Unit>
) = _wheneverCapture(function, result, { lastValue(argA, argB, argC) })

fun <A, B, C> invokeWheneverCapture(
    argA: A,
    argB: B,
    argC: C,
    function: CaptorFnc<(A, B, C) -> Unit>
) = invokeWheneverCaptureWithResult(argA, argB, argC, Unit, function)

fun <A, B, C> invokeWheneverCaptureWithResult(
    argA: A,
    argB: B,
    argC: C,
    result: Any,
    function: CaptorFnc<(A, B, C) -> Unit>
) = _wheneverCapture(function, result, { lastValue(argA, argB, argC) }, _throwIfCapturedTwice)
//endregion

//region Four Arguments
fun <A, B, C, D> invokeLastWheneverCapture(
    argA: A,
    argB: B,
    argC: C,
    argD: D,
    function: CaptorFnc<(A, B, C, D) -> Unit>
) = invokeLastWheneverCaptureWithResult(argA, argB, argC, argD, Unit, function)

fun <A, B, C, D> invokeLastWheneverCaptureWithResult(
    argA: A,
    argB: B,
    argC: C,
    argD: D,
    result: Any,
    function: CaptorFnc<(A, B, C, D) -> Unit>
) = _wheneverCapture(function, result, { lastValue(argA, argB, argC, argD) })

fun <A, B, C, D> invokeWheneverCapture(
    argA: A,
    argB: B,
    argC: C,
    argD: D,
    function: CaptorFnc<(A, B, C, D) -> Unit>
) = invokeWheneverCaptureWithResult(argA, argB, argC, argD, Unit, function)

fun <A, B, C, D> invokeWheneverCaptureWithResult(
    argA: A,
    argB: B,
    argC: C,
    argD: D,
    result: Any,
    function: CaptorFnc<(A, B, C, D) -> Unit>
) = _wheneverCapture(function, result, { lastValue(argA, argB, argC, argD) }, _throwIfCapturedTwice)
//endregion

private inline fun <reified T : Any> _wheneverCapture(
    function: CaptorFnc<T>,
    result: Any,
    crossinline useCaptor: CaptorFnc<T>,
    crossinline validate: (KArgumentCaptor<T>) -> Unit = {},
) {
    argumentCaptor<T> {
        whenever(function(this)).then {
            validate(this)
            useCaptor.invoke(this, this)
            return@then result
        }
    }
}

private val _throwIfCapturedTwice: (KArgumentCaptor<Any>) -> Unit = {
    if (it.firstValue != it.lastValue) {
        throw IllegalStateException(
            "Lambda was captured more than once, if you are only interested in the last value - call invokeLastWheneverCapture"
        )
    }
}
