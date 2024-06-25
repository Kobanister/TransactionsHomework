package com.transactions.homework.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transactions.homework.domain.common.model.ResultObject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseVM : ViewModel(), ProgressDispatcher {

    override val progressFlow = MutableSharedFlow<Boolean>(1)

    var errorFlow = MutableSharedFlow<Throwable>()

    override suspend fun showProgress() {
        progressFlow.emit(true)
    }

    override suspend fun hideProgress() {
        progressFlow.emit(false)
    }

    fun onError(error: Throwable) {
        errorFlow.emitViewModelScope(error)
    }

    fun onError(error: ResultObject.Error) {
        errorFlow.emitViewModelScope(error.errorCause)
    }

    suspend fun <T> SharedFlow<T>.emit(value: T) {
        if (this is MutableSharedFlow<T>) {
            emit(value)
        } else {
            throw IllegalStateException("$this is not MutableSharedFlow. Cannot perform emit()")
        }
    }

    fun <T> SharedFlow<T>.emitViewModelScope(value: T) {
        viewModelScope.launch {
            emit(value)
        }
    }

    fun SharedFlow<Unit>.emitViewModelScope() {
        viewModelScope.launch {
            emit(Unit)
        }
    }

    suspend fun SharedFlow<Unit>.emit() {
        if (this is MutableSharedFlow) {
            emit(Unit)
        } else {
            throw IllegalStateException("$this is not MutableSharedFlow. Cannot perform emit()")
        }
    }
}
