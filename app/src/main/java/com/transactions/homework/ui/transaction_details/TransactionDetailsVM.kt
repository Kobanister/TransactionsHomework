package com.transactions.homework.ui.transaction_details

import androidx.lifecycle.viewModelScope
import com.transactions.homework.data.transaction.TransactionUIModel
import com.transactions.homework.domain.transaction.GetTransactionByIdUseCase
import com.transactions.homework.domain.transaction.TransactionByIdRequest
import com.transactions.homework.ui.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionDetailsVM @Inject constructor(private val getTransactionByIdUseCase: GetTransactionByIdUseCase): BaseVM() {
    val transactionDetailsFlow = MutableSharedFlow<TransactionUIModel>()

    fun onCreate(transactionId: String) {
        viewModelScope.launch {
            transactionDetailsFlow.emit(getTransactionByIdUseCase(TransactionByIdRequest(transactionId)))
        }
    }
}
