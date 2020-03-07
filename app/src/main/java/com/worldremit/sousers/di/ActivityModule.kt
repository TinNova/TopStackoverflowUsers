package com.worldremit.sousers.di

import com.worldremit.sousers.ui.detail.DetailActivity
import com.worldremit.sousers.ui.userlist.UserListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): UserListActivity

    @ContributesAndroidInjector
    abstract fun bindDetailActivity(): DetailActivity
}

//@Module
//class ActivityModule(private var activity: Activity) {
//
//    @Provides
//    fun provideActivity(): Activity {
//        return activity
//    }
//
//    @Provides
//    fun providePresenter(): UserListPresenter {
//        return UserListPresenter()
//    }

//}