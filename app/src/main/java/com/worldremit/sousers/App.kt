package com.worldremit.sousers

import android.app.Application
import com.google.gson.GsonBuilder
import com.worldremit.sousers.repository.UsersRepository
import com.worldremit.sousers.repository.UsersRepositoryApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    val retrofit: Retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl("https://api.stackexchange.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    val usersRepository: UsersRepository = UsersRepositoryApi(retrofit)
}