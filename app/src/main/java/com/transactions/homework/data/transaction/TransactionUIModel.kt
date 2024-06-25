package com.transactions.homework.data.transaction

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionUIModel(
    val name: String,
    val accountNumber: String,
    val amount: Double,
    val description: String?,
    val date: String
): Parcelable
