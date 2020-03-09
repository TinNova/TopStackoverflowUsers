package com.worldremit.sousers.ui.userlist

import androidx.lifecycle.MutableLiveData
import com.worldremit.sousers.common.DisposingViewModel
import com.worldremit.sousers.repository.UsersRepository
import com.worldremit.sousers.ui.SanitisedUser
import com.worldremit.sousers.ui.ViewStateModel
import com.worldremit.sousers.mapToUserSql
import com.worldremit.sousers.ui.SqlUserState
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    private val userRepo: UsersRepository
) :
    DisposingViewModel() {

    val viewState = MutableLiveData<ViewStateModel>()
    val sqlUserState = MutableLiveData<SqlUserState>()

    fun onViewLoaded() {
        fetchUsers()
    }

    private fun fetchUsers() {
        add(userRepo.fetchUsers().subscribe(
            {
                viewState.value = ViewStateModel(
                    sanitisedUsers = it,
                    isDataReady = true,
                    isLoading = false,
                    isNetworkError = false
                )
            }, {
                viewState.value = ViewStateModel(
                    isDataReady = false,
                    isLoading = false,
                    isNetworkError = true
                )
            }
        ))
    }

    private fun saveUserStatus(user: SanitisedUser, userStateChange: UserStateChange) {
        // IMPROVEMENT: Delete the user if both isBlocked and isFollowed are false
        // 1. Return a query of the user
        // 2. If user doesn't exist -> save user
        // 3. If user exists, but both booleans WON'T be false -> save user
        // 4. If user exists, and both booleans WILL be false -> delete user
        add(userRepo.insertUser(user.mapToUserSql()).subscribe(
            {
                sqlUserState.value = SqlUserState(
                    sanitisedUser = user,
                    userStateChange = userStateChange,
                    isSqlError = false
                )
            }, {
                sqlUserState.value = SqlUserState(
                    userStateChange = userStateChange,
                    isSqlError = true
                )
            }
        ))
    }

    fun saveUserFollowStatus(user: SanitisedUser) {
        user.isFollowed = !user.isFollowed
        saveUserStatus(user, UserStateChange.FOLLOW)
    }

    fun saveUserBlockStatus(user: SanitisedUser) {
        user.isBlocked = !user.isBlocked
        saveUserStatus(user, UserStateChange.BLOCK)
    }

    enum class UserStateChange {
        FOLLOW,
        BLOCK
    }

}