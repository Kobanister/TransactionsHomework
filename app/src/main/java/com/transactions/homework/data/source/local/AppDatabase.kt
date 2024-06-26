package com.transactions.homework.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.transactions.homework.data.source.local.model.TransactionLocal

@Database(entities = [TransactionLocal::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
