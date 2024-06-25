package com.transactions.homework.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.transactions.homework.domain.common.model.ResultObject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseVM : ViewModel(), ProgressDispatcher {

    override val progressFlow = MutableSharedFlow<Boolean>(1)

    var errorFlow = MutableSharedFlow<Throwable>()
    var navigationFlow = MutableSharedFlow<NavDirections>()

    override suspend fun showProgress() {
        progressFlow.emit(true)
    }

    override suspend fun hideProgress() {
        progressFlow.emit(false)
    }

    fun onError(error: ResultObject.Error) {
        errorFlow.emitViewModelScope(error.errorCause)
    }

    protected fun navigate(direction: NavDirections) {
        navigationFlow.emitViewModelScope(direction)
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
}
