package com.transactions.homework.ui.transactions

import androidx.lifecycle.viewModelScope
import com.transactions.homework.data.transaction.TransactionUIModel
import com.transactions.homework.domain.common.extensions.runIfError
import com.transactions.homework.domain.common.extensions.runIfSuccess
import com.transactions.homework.domain.transaction.GetTransactionsListUseCase
import com.transactions.homework.ui.base.BaseVM
import com.transactions.homework.ui.transactions.model.AccountBalance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsVM @Inject constructor(private val getTransactionsListUseCase: GetTransactionsListUseCase): BaseVM() {

    val transactionsListFlow = MutableStateFlow<ArrayList<TransactionUIModel>>(arrayListOf())
    val accountBalanceFlow = MutableStateFlow(AccountBalance(0.0))

    init {
       fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val result = getTransactionsListUseCase()
            result.runIfSuccess {
                calculateAccountBalance(it)
                transactionsListFlow.emit(it)
            }
            result.runIfError { onError(it) }
        }
    }

    private fun calculateAccountBalance(items: List<TransactionUIModel>) {
        viewModelScope.launch {
            val balanceSum = items.sumOf { it.amount }
            accountBalanceFlow.emit(AccountBalance(balanceSum))
        }
    }

    fun onRefresh() {
        fetchData()
    }

    fun onTransactionItemClick(transaction: TransactionUIModel) {
        navigate(TransactionsFragmentDirections.openTransactionDetails(transaction))
    }
}
