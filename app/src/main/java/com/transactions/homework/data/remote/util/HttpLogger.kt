package com.transactions.homework.data.remote.util

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton

private const val NETWORK_TAG = "NetworkAPI"

@Singleton
class HttpLogger @Inject constructor() : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.d(NETWORK_TAG, message)
    }
}
