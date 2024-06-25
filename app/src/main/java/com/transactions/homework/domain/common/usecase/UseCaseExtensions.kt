package com.transactions.homework.domain.common.usecase

import com.transactions.homework.domain.common.model.ResultObject

suspend fun <T> useNetwork(
    networkCall: suspend () -> T
): ResultObject<T> = try {
    val networkResponse = networkCall()
    ResultObject.Success(networkResponse)
} catch (t: Throwable) {
    ResultObject.Error(t)
}
