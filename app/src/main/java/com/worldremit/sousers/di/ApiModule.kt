package com.worldremit.sousers.di

import com.worldremit.sousers.api.StackOverflowApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Reusable
    fun providesRetrofit(okHttpClient: OkHttpClient): StackOverflowApi =
        Retrofit.Builder()
            .baseUrl("https://api.stackexchange.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(StackOverflowApi::class.java)

    @Provides
    @Reusable
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
//            .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
//            .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
//            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
//            .cache(null)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
}