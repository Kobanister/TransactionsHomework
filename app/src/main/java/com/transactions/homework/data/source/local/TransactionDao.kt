package com.transactions.homework.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.transactions.homework.data.source.local.model.TransactionLocal

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions")
    suspend fun getAll(): List<TransactionLocal>

    @Upsert
    suspend fun upsertAll(tasks: List<TransactionLocal>)

    @Query("DELETE FROM transactions")
    suspend fun deleteAll()

    @Query("SELECT * FROM transactions WHERE id = :transactionId")
    suspend fun getById(transactionId: String): TransactionLocal
}
