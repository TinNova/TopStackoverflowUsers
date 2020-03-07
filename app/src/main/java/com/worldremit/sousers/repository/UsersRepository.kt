package com.worldremit.sousers.repository

import com.worldremit.sousers.api.StackOverflowApi
import com.worldremit.sousers.mapToSanitisedUser
import com.worldremit.sousers.sql.UserDao
import com.worldremit.sousers.sql.UserSql
import com.worldremit.sousers.ui.SanitisedUser
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val stackOverflowApi: StackOverflowApi,
    private val userDao: UserDao,
    private val usersHelper: UsersHelper
) {

    fun fetchUsers(): Single<List<SanitisedUser>> = Single.zip(
        fetchTopUsers(),
        fetchSavedUsers(),
        BiFunction<List<SanitisedUser>, List<UserSql>, List<SanitisedUser>> { sanitisedUser, sqlUsers ->
            usersHelper.returnUpdatedUsers(sanitisedUser, sqlUsers)
        })

    private fun fetchTopUsers(): Single<List<SanitisedUser>> = stackOverflowApi.topUsers()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { response -> response.users }
        .flattenAsObservable { it }
        .map { it.mapToSanitisedUser() }
        .toList()

    private fun fetchSavedUsers(): Single<List<UserSql>> = userDao.getUser()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun insertUser(user: UserSql): Completable = userDao.insertUser(user)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}
