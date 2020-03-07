package com.worldremit.sousers.sql

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserSql::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        const val DATABASE_NAME: String = "app_db"
    }
}