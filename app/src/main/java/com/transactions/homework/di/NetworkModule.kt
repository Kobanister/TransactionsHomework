package com.transactions.homework.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.transactions.homework.BuildConfig
import com.transactions.homework.data.remote.ConnectionTimeoutInterceptor
import com.transactions.homework.data.remote.api.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        connectionTimeoutInterceptor: ConnectionTimeoutInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(connectionTimeoutInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        httpUrl: HttpUrl,
        client: OkHttpClient,
        gson: Gson,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(httpUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpUrl(): HttpUrl = BuildConfig.BASE_URL.toHttpUrl()
}