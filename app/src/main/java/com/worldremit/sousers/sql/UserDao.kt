package com.worldremit.sousers.sql

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {

    /**
     * OnConflictStrategy.REPLACE
     *
     * REPLACE is used because a user could be followed, prompting them to be saved
     *         that same user could then be blocked, in which case we will replace */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userSql: UserSql): Completable

    @Update
    fun updateUser(userSql: UserSql)

    @Query("SELECT * FROM usersql")
    fun getUser(): Single<List<UserSql>>
}