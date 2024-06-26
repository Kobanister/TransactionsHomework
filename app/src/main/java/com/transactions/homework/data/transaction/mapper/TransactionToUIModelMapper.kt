package com.transactions.homework.data.transaction.mapper

import com.transactions.homework.data.source.local.model.TransactionLocal
import com.transactions.homework.data.transaction.TransactionRemote
import com.transactions.homework.data.transaction.TransactionType
import com.transactions.homework.data.transaction.TransactionUIModel
import com.transactions.homework.util.datetime.TransactionDateTimeFormatter
import javax.inject.Inject

class TransactionToUIModelMapper @Inject constructor(private val dateTimeFormatter: TransactionDateTimeFormatter) {

    fun mapRemoteToExternal(model: TransactionRemote): TransactionUIModel {
        return with(model) {
            val transactionType = TransactionType.typeOf(type)
            val transactionAmount = amount?.toDoubleOrNull() ?: 0.0
            TransactionUIModel(
                name = name ?: "N/A",
                accountNumber = accountNumber ?: "N/A",
                amount = if (transactionType == TransactionType.DEBIT) transactionAmount.unaryMinus() else transactionAmount,
                description = description,
                date = date?.let { dateTimeFormatter.formatTime(dateTimeFormatter.parseTime(it)) } ?: "N/A"
            )
        }
    }

    fun mapRemoteToLocal(model: TransactionRemote): TransactionLocal {
        return with(model) {
            TransactionLocal(
                id = id ?: "",
                name = name,
                accountNumber = accountNumber,
                type = type,
                amount = amount,
                description = description,
                date = date
            )
        }
    }

    fun mapLocalToExternal(model: TransactionLocal): TransactionUIModel {
        return with(model) {
            val transactionType = TransactionType.typeOf(type)
            val transactionAmount = amount?.toDoubleOrNull() ?: 0.0
            TransactionUIModel(
                name = name ?: "N/A",
                accountNumber = accountNumber ?: "N/A",
                amount = if (transactionType == TransactionType.DEBIT) transactionAmount.unaryMinus() else transactionAmount,
                description = description,
                date = date?.let { dateTimeFormatter.formatTime(dateTimeFormatter.parseTime(it)) } ?: "N/A"
            )
        }
    }
}
