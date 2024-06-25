package com.transactions.homework.ui.transactions

import com.transactions.homework.data.transaction.TransactionModel
import com.transactions.homework.ui.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionsVM @Inject constructor(): BaseVM() {
    fun onTransactionItemClick(transactions: TransactionModel) {
        TODO("Not yet implemented")
    }
}
