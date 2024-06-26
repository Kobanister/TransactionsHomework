package com.transactions.homework.data.source.remote

import com.transactions.homework.data.source.remote.api.Api
import com.transactions.homework.data.transaction.TransactionRemote
import com.transactions.homework.domain.common.model.ResultObject
import com.transactions.homework.domain.common.usecase.useNetwork
import javax.inject.Inject

class TransactionsDataSourceImpl @Inject constructor(private val api: Api): TransactionsDataSource {
    override suspend fun getTransactionsList(): ResultObject<List<TransactionRemote>> {
        return useNetwork { api.getTransactionsList() }
    }
}
