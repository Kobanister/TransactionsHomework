package com.transactions.homework.domain.transaction

import com.transactions.homework.data.transaction.TransactionUIModel
import com.transactions.homework.domain.Repository
import com.transactions.homework.domain.common.usecase.BackgroundUseCase
import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@Reusable
class GetTransactionByIdUseCase @Inject constructor(
    private val repository: Repository
) : BackgroundUseCase<TransactionByIdRequest, TransactionUIModel>() {

    override suspend fun executeRequest(
        request: TransactionByIdRequest,
        coroutineScope: CoroutineScope,
    ): TransactionUIModel = repository.getTransactionById(request.transactionId)
}

class TransactionByIdRequest(val transactionId: String)
