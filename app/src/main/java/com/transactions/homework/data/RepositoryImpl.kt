package com.transactions.homework.data

import com.transactions.homework.data.source.remote.TransactionsDataSource
import com.transactions.homework.data.transaction.TransactionUIModel
import com.transactions.homework.data.transaction.mapper.TransactionToUIModelMapper
import com.transactions.homework.domain.Repository
import com.transactions.homework.domain.common.extensions.mapIfSuccess
import com.transactions.homework.domain.common.extensions.toSuccessResult
import com.transactions.homework.domain.common.model.ResultObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val transactionsDataSource: TransactionsDataSource,
    private val transactionsMapper: TransactionToUIModelMapper
) : Repository {
    override suspend fun getTransactionsList(): ResultObject<List<TransactionUIModel>> {
        return transactionsDataSource.getTransactionsList().mapIfSuccess {
            items -> items.map { transactionsMapper.map(it) }.toSuccessResult()
        }
    }
}