package com.eunbi.character.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> makeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    call: suspend () -> T
): Result<T> = runCatching {
    withContext(context = dispatcher) {
        call.invoke()
    }
}

suspend fun <T> apiCallAndReturnThrow(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    call: suspend () -> T
): T {
    val result = makeApiCall(dispatcher, call)
    return result.getOrThrow()
}