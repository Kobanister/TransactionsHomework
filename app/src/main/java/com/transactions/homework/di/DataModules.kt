package com.transactions.homework.di

import android.content.Context
import androidx.room.Room
import com.transactions.homework.data.RepositoryImpl
import com.transactions.homework.data.source.local.AppDatabase
import com.transactions.homework.data.source.local.TransactionDao
import com.transactions.homework.data.source.remote.TransactionsDataSource
import com.transactions.homework.data.source.remote.TransactionsDataSourceImpl
import com.transactions.homework.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DB_NAME = "TransactionsHomework.db"

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindRepository(repository: RepositoryImpl): Repository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindTransactionsDataSource(dataSource: TransactionsDataSourceImpl): TransactionsDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    fun provideTransactionDao(database: AppDatabase): TransactionDao = database.transactionDao()
}
