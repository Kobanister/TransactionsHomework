package com.transactions.homework.domain.transaction

import com.transactions.homework.data.remote.api.Api
import com.transactions.homework.data.transaction.TransactionUIModel
import com.transactions.homework.data.transaction.mapper.TransactionToUIModelMapper
import com.transactions.homework.domain.common.extensions.mapIfSuccess
import com.transactions.homework.domain.common.extensions.toSuccessResult
import com.transactions.homework.domain.common.model.ResultObject
import com.transactions.homework.domain.common.usecase.BackgroundNoParamsUseCase
import com.transactions.homework.domain.common.usecase.useNetwork
import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@Reusable
class GetTransactionsListUseCase @Inject constructor(
    private val api: Api,
    private val mapper: TransactionToUIModelMapper,
) : BackgroundNoParamsUseCase<ResultObject<List<TransactionUIModel>>>() {

    override suspend fun executeRequest(
        request: Unit,
        coroutineScope: CoroutineScope,
    ): ResultObject<List<TransactionUIModel>> {
        return useNetwork { api.getTransactionsList() }.mapIfSuccess { items ->
            items.map { mapper.map(it) }.toSuccessResult()
        }
    }
}
