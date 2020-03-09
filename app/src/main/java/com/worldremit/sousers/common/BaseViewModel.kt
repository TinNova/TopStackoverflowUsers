package com.worldremit.sousers.common

import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Base/Abstract ViewModel class which provides CompositeDisposable and is lifecycle aware for clearing subscriptions.
 */
abstract class DisposingViewModel : ViewModel(), LifecycleObserver {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun cleanUp() {
        compositeDisposable.clear()
    }
}