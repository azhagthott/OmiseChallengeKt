package dev.fb.android.tamboon.base

import io.reactivex.observers.DisposableObserver

class UseCaseObserver<T> : DisposableObserver<T>() {

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
    }

    override fun onComplete() {
    }
}
