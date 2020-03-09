package com.worldremit.sousers.repository

import com.worldremit.sousers.sql.UserSql
import com.worldremit.sousers.ui.SanitisedUser
import javax.inject.Inject

class UsersHelper @Inject constructor() {

    fun returnUpdatedUsers(
        sanitisedUsers: List<SanitisedUser>,
        sqlUsers: List<UserSql>
    ): List<SanitisedUser> {

        // IMPROVEMENTS:
        // check which list is smallest, you should always start with the smallest list
        // 1. Compare size of sanitisedUsers list to size of sqlUsers list
        // 2. Begin the comparison with the smallest list

        sqlUsers.forEach { sqlUser ->

            sanitisedUsers.forEach { user ->
                if (user.userId == sqlUser.userId) {

                    user.isFollowed = sqlUser.isFollowed
                    user.isBlocked = sqlUser.isBlocked
                }
            }
        }
        return sanitisedUsers
    }
}