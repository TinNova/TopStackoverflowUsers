package com.worldremit.sousers.di

import android.app.Application
import androidx.room.Room
import com.worldremit.sousers.sql.AppDatabase
import com.worldremit.sousers.sql.AppDatabase.Companion.DATABASE_NAME
import com.worldremit.sousers.sql.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SqlModule {

    @Provides
    @Singleton
    fun provideAppDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.getUserDao()
    }
}