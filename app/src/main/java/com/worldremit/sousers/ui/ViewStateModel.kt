package com.worldremit.sousers.ui

data class ViewStateModel(
    val listData: List<SanitisedUser> = listOf(),
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
    var isFollowed: Boolean,
    var isBlocked: Boolean
)