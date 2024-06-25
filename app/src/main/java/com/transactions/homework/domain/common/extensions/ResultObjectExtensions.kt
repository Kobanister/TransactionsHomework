package com.transactions.homework.domain.common.extensions

import com.transactions.homework.domain.common.model.ResultObject

inline fun <T, P> ResultObject<T>.mapIfSuccess(transformation: (T) -> ResultObject<P>): ResultObject<P> =
    when (this) {
        is ResultObject.Success -> transformation(this.value)
        is ResultObject.Error -> this
    }

inline fun <T> ResultObject<T>.runIfSuccess(action: (T) -> Unit) {
    if (this is ResultObject.Success) {
        action(this.value)
    }
}

inline fun <T> ResultObject<T>.runIfError(action: (ResultObject.Error) -> Unit) {
    if (this is ResultObject.Error) {
        action(this)
    }
}

fun <T : Any> T.toSuccessResult(): ResultObject.Success<T> = ResultObject.Success(this)

fun <T : Throwable> T.toErrorResult(): ResultObject.Error = ResultObject.Error(this)
