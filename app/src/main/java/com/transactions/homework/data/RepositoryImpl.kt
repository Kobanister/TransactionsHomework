package com.transactions.homework.data

import com.transactions.homework.data.source.local.TransactionDao
import com.transactions.homework.data.source.remote.TransactionsDataSource
import com.transactions.homework.data.transaction.TransactionUIModel
import com.transactions.homework.data.transaction.mapper.TransactionToUIModelMapper
import com.transactions.homework.domain.Repository
import com.transactions.homework.domain.common.extensions.mapIfError
import com.transactions.homework.domain.common.extensions.mapIfSuccess
import com.transactions.homework.domain.common.extensions.toSuccessResult
import com.transactions.homework.domain.common.model.ResultObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val transactionsDataSource: TransactionsDataSource,
    private val transactionsDao: TransactionDao,
    private val transactionsMapper: TransactionToUIModelMapper
) : Repository {
    override suspend fun getTransactionsList(): ResultObject<List<TransactionUIModel>> {
        return transactionsDataSource.getTransactionsList().mapIfSuccess { items ->
            transactionsDao.upsertAll(items.map { transactionsMapper.mapRemoteToLocal(it) })
            items.map { transactionsMapper.mapRemoteToExternal(it) }.toSuccessResult()
        }.mapIfError { error ->
            val externalItems = transactionsDao.getAll().map { transactionsMapper.mapLocalToExternal(it) }
            if (externalItems.isNotEmpty())
                externalItems.toSuccessResult()
            else
                ResultObject.Error(error)
        }
    }
}
