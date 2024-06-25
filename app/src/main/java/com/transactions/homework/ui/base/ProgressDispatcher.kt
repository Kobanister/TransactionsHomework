package com.transactions.homework.ui.base

import kotlinx.coroutines.flow.MutableSharedFlow

interface ProgressDispatcher {
    suspend fun showProgress()
    suspend fun hideProgress()
    val progressFlow: MutableSharedFlow<Boolean>
}
