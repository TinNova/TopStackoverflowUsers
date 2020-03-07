package com.worldremit.sousers

import com.worldremit.sousers.di.AppComponent
import com.worldremit.sousers.di.DaggerAppComponent
import dagger.android.DaggerApplication
import dagger.android.AndroidInjector

class App : DaggerApplication() {

    override fun applicationInjector():
            AndroidInjector<out DaggerApplication> {

        //Build app component
        val appComponent: AppComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        //inject application instance
        appComponent.inject(this)
        return appComponent
    }

//    val retrofit: Retrofit = Retrofit.Builder()
//            .client(OkHttpClient
//                .Builder()
//                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .build())
//            .baseUrl("https://api.stackexchange.com")
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
//            .build()
//
//    val usersRepository: UsersRepository = UsersRepositoryApi(retrofit)


}