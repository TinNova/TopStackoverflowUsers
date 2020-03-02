package com.worldremit.sousers.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseMvpPresenter {

    private val compositeDisposable = CompositeDisposable()

    abstract fun onCreate(view: BaseMvpView)

    abstract fun onStart()

    open fun onDestroy() {
        compositeDisposable.clear()
    }

    protected fun observe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}