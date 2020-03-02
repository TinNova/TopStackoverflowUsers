package com.worldremit.sousers.repository

import com.worldremit.sousers.api.StackOverflowApi
import com.worldremit.sousers.api.model.User
import io.reactivex.Single
import retrofit2.Retrofit

class UsersRepositoryApi(private val retrofit: Retrofit) : UsersRepository {

    private var api: StackOverflowApi

    init {
        api = retrofit.create(StackOverflowApi::class.java)
    }

    override fun fetchTopUsers(): Single<List<User>> {
        return api.topUsers.map { response -> response.users }
    }
}