package com.worldremit.sousers

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
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

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()