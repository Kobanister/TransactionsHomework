package com.transactions.homework.domain

import com.transactions.homework.data.transaction.TransactionUIModel
import com.transactions.homework.domain.common.model.ResultObject

interface Repository {
    suspend fun getTransactionsList(): ResultObject<List<TransactionUIModel>>
}
