package com.worldremit.sousers.ui

import com.worldremit.sousers.ui.userlist.UserListViewModel

data class ViewStateModel(
    val sanitisedUsers: List<SanitisedUser> = emptyList(),
    val isDataReady: Boolean = false,
    val isLoading: Boolean = true,
    val isNetworkError: Boolean = false
)

data class SanitisedUser(
    val userId: Int = 0,
    val userName: String? = "",
    val profileImage: String? = "",
    val memberSince: Int? = 0, //creationDate
    val location: String? = "",
    val reputation: Int? = 0,
    var isFollowed: Boolean = false,
    var isBlocked: Boolean = false
)

data class SqlUserState(
    val sanitisedUser: SanitisedUser = SanitisedUser(),
    val userStateChange: UserListViewModel.UserStateChange,
    val isSqlError: Boolean = false
)