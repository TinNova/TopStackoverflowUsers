package com.worldremit.sousers

import android.app.Activity
import com.worldremit.sousers.api.model.User
import com.worldremit.sousers.sql.UserSql
import com.worldremit.sousers.ui.SanitisedUser

/**
 * map User to SanitisedUser
 */
fun User.mapToSanitisedUser() =
    SanitisedUser(
        userId,
        displayName,
        profileImage,
        creationDate,
        location,
        reputation,
        isBlocked,
        isFollowed
    )

/**
 * map SanitisedUser to UserSql
 */
fun SanitisedUser.mapToUserSql() = UserSql(userId, isFollowed, isBlocked)