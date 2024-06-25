package com.transactions.homework

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class TransactionsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
