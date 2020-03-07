package com.worldremit.sousers.sql

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserSql(
    @PrimaryKey val userId: Int = 0,
    val isFollowed: Boolean = false,
    val isBlocked: Boolean = false
)