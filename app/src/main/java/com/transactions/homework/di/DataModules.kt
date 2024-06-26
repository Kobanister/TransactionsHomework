package com.transactions.homework.di

import com.transactions.homework.data.RepositoryImpl
import com.transactions.homework.data.source.remote.TransactionsDataSource
import com.transactions.homework.data.source.remote.TransactionsDataSourceImpl
import com.transactions.homework.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
