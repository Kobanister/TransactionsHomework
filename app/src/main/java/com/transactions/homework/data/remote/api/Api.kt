package com.transactions.homework.data.remote.api

import com.transactions.homework.data.transaction.TransactionRemote
import retrofit2.http.GET

interface Api {
    @GET("transactions")
    fun getTransactionsList(): List<TransactionRemote>
}
