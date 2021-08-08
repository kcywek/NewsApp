package com.test.newsapp.data.repository.exception

import com.google.gson.JsonParseException
import com.squareup.moshi.JsonDataException
import com.test.common.kotlin.result.ResultEntity
import com.test.common.kotlin.result.ResultEntity.Companion.failure
import com.test.common.kotlin.result.ResultEntity.Companion.nothing
import com.test.common.kotlin.result.ResultEntity.Companion.success
import com.test.common.kotlin.result.ResultEntityNothing
import com.test.common.kotlin.result.combine
import com.test.newsapp.domain.common.model.NetworkException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException
import java.io.IOException

internal suspend inline fun wrapNetworkResultNothing(crossinline block: suspend CoroutineScope.() -> Unit): ResultEntityNothing =
    wrapNetworkResult(block).then { nothing() }

internal suspend inline fun <T> wrapNetworkResult(crossinline block: suspend CoroutineScope.() -> T): ResultEntity<T> =
    try {
        coroutineScope {
            success(block())
        }
    } catch (e: JsonDataException) {
        failure(NetworkException.Client("Response Parsing Failed"))
    } catch (e: JsonParseException) {
        failure(NetworkException.Client("Response Parsing Failed"))
    } catch (e: IOException) {
        failure(NetworkException.Client("Connectivity Failed"))
    } catch (e: HttpException) {
        when (e.code()) {
            401 -> failure(NetworkException.Unauthenticated())
            in 400..499 -> failure(NetworkException.Client("Request Failed with ${e.code()}"))
            in 500..599 -> failure(NetworkException.Server("Request Failed with ${e.code()}"))
            else -> failure(NetworkException.Unexpected("Request Failed with ${e.code()}"))
        }
    }

internal suspend inline fun <R, T> List<R>.wrapListResult(crossinline block: suspend CoroutineScope.(R) -> T): ResultEntity<List<T>> =
    coroutineScope {
        map { item ->
            async {
                wrapNetworkResult {
                    block(item)
                }
            }
        }
            .awaitAll()
            .combine()
    }
