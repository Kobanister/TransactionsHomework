package com.transactions.homework.data.transaction

enum class TransactionType {
    DEBIT,
    CREDIT;

    companion object {
        fun typeOf(type: String?): TransactionType? = entries.find { it.name.lowercase() == type }
    }
}
