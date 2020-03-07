package com.worldremit.sousers.ui.userlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.worldremit.sousers.common.DisposingViewModel
import com.worldremit.sousers.repository.UsersRepository
import com.worldremit.sousers.ui.SanitisedUser
import com.worldremit.sousers.ui.ViewStateModel
import com.worldremit.sousers.mapToUserSql
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    private val userRepo: UsersRepository,
    application: Application
) : DisposingViewModel(application) {

    val viewState = MutableLiveData<ViewStateModel>()

    init {
        viewState.value = ViewStateModel()
    }

    fun onViewLoaded() {
        fetchUsers()
    }

    private fun fetchUsers() {
        add(userRepo.fetchUsers().subscribe(
            {
                viewState.value = viewState.value?.copy(
                    listData = it,
                    isDataReady = true,
                    isLoading = false,
                    isNetworkError = false
                )
            }, {
                viewState.value = viewState.value?.copy(
                    isDataReady = false,
                    isLoading = false,
                    isNetworkError = true
                )
            }
        ))
    }

    private fun saveUserStatus(user: SanitisedUser) {
        // IMPROVEMENT: Delete the user if both isBlocked and isFollowed are false
        // 1. Return a query of the user first
        // 2. If user doesn't exist -> save user
        // 3. If user exists, but both booleans WON'T be false -> save user
        // 4. If user exists, and both booleans WILL be false -> delete user
        add(userRepo.insertUser(user.mapToUserSql()).subscribe(
            {}, {
                it.stackTrace
            }
        ))
    }

    fun saveUserFollowStatus(user: SanitisedUser) {
        user.isFollowed = !user.isFollowed
        saveUserStatus(user)
    }

    fun saveUserBlockStatus(user: SanitisedUser) {
        user.isBlocked = !user.isBlocked
        saveUserStatus(user)
    }

}