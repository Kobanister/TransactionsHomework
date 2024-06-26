package com.transactions.homework.data.source.remote

import com.transactions.homework.data.transaction.TransactionRemote
import com.transactions.homework.domain.common.model.ResultObject

interface TransactionsDataSource {
    suspend fun getTransactionsList(): ResultObject<List<TransactionRemote>>
}
