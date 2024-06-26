package com.transactions.homework.data.source.remote.api

import com.transactions.homework.data.transaction.TransactionRemote
import retrofit2.http.GET

interface Api {
    @GET("transactions")
    suspend fun getTransactionsList(): List<TransactionRemote>
}
