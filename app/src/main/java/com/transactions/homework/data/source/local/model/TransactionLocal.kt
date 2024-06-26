package com.transactions.homework.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
class TransactionLocal(
    @PrimaryKey val id: String,
    val name: String?,
    val accountNumber: String?,
    val type: String?,
    val amount: String?,
    val description: String?,
    val date: String?
)
